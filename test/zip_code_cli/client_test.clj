(ns zip-code-cli.client-test
  (:require [clojure.test :refer :all]
            [zip-code-cli.client :as client]
            [vcr-clj.clj-http :refer [with-cassette]]))

(deftest get-zip-code-info-test
  (testing "status code should be 200 when call is successful"
    (with-cassette :zip-code-success
      (let [resp (client/get-zip-code-info 33162)
            headers (:headers resp)]
        (is (= 200 (get resp :status)))
        (is (= "application/json" (get headers :content-type))))))

  (testing "should thorw exception for invalid pincode"
    (with-cassette :zip-code-failure
      (is (thrown? Exception (client/get-zip-code-info 213412))))))
