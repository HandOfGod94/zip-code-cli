(ns zip-code-cli.client
  (:require [clj-http.client :as client]))

(def ^:const base-url "https://api.zippopotam.us/us/")

(defn get-zip-code-info [pin-code]
  (client/get (str base-url pin-code) {:as :json}))
