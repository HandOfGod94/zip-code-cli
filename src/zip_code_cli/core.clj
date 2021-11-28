(ns zip-code-cli.core
  (:require [clojure.tools.cli :as cli]
            [clojure.string :as string])
  (:gen-class))

(def cli-options [["-c" "--zip-code ZIPCODE" "Zip code of a US state"
                   :default 000000]
                  ["-h", "--help", "display help for the command"]])

(defn  usage-summary [option-summary]
  (->> ["A small CLI app written in clojure to help out with zipcodes"
        ""
        "Usage: zip [option] code"
        ""
        option-summary]
       (string/join \newline)))

(defn parse [args]
  (let [{:keys [options arguments errors summary]} (cli/parse-opts args cli-options)]
    (cond
      (:help options) {:message (usage-summary summary)}
      errors {:message (format "Ooops!! Something went wrong \n %s" summary)}
      (first arguments) {:message (str "Fetching info for zip-code: " (first arguments))})))


(defn -main
  [& args]
  (let [{:keys [message]} (parse args)]
    (println message)))
