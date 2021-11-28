(ns zip-code-cli.client-test
  (:require [clojure.test :refer :all]
            [zip-code-cli.client :as client]
            [vcr-clj.clj-http :refer [with-cassette]]))

(def ^:const valid-pin 33162)
(def ^:const invalid-pin 213142)

(deftest get-zip-code-info-test
  (testing "status code should be 200 when call is successful"
    (with-cassette :zip-code-success
      (let [resp (client/get-zip-code-info valid-pin)
            headers (:headers resp)]
        (is (= 200 (get resp :status)))
        (is (= "application/json" (get headers :content-type))))))

  (testing "should thorw exception for invalid pincode"
    (with-cassette :zip-code-failure
      (is (thrown? Exception (client/get-zip-code-info invalid-pin)))))

  (testing "should return zipcode info for valid pincode"
    (with-cassette :zip-code-success
      (let [resp (:body (client/get-zip-code-info valid-pin))
            post-code (:post-code resp)
            country (:country resp)
            country-abbreviation (:country-abbreviation resp)
            places (:places resp)]
        (is (= (str valid-pin) post-code))
        (is (= "US" country-abbreviation))
        (is (= "United States" country))
        (is (= "Florida" (:state (first places))))))))

(deftest kebabizer-test
  (testing "should replace space with hyphen in json keys"
    (let [result (client/kebabizer "foo bar")]
      (is (= :foo-bar result)))))
