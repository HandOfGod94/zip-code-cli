(ns zip-code-cli.core-test
  (:require [clojure.test :refer :all]
            [zip-code-cli.core :refer :all]
            [clojure.string :as string]))

(deftest usage-summary-test
  (testing "should print help summary with --help flag"
    (let [text (:message (parse ["--help"]))]
      (is (string/includes? text "Usage"))
      (is (string/includes? text "--zip-code"))
      (is (string/includes? text "--help")))))
