(defproject zip-code-cli "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clj-http "3.12.3"]
                 [com.gfredericks/vcr-clj "0.4.22"]
                 [cheshire "5.10.1"]
                 [org.clojure/tools.cli "1.0.206"]]
  :main ^:skip-aot zip-code-cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
