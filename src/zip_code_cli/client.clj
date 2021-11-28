(ns zip-code-cli.client
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as string]))

(def ^:const base-url "https://api.zippopotam.us/us/")

(defn kebabizer [raw-str]
  (-> raw-str
      (string/replace #"\s+" "-")
      (keyword)))

(defn get-zip-code-info [pin-code]
  (let [resp (client/get (str base-url pin-code))]
    (as-> resp v
      (:body v)
      (json/read-str v :key-fn kebabizer)
      (assoc-in resp [:body] v))))
