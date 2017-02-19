(defproject ysera "0.1.0-SNAPSHOT"
  :description "Useful tools for Clojure"
  :url ""
  :license {}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]]
  :main ^:skip-aot ysera.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})