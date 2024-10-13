(defproject ysera "2.0.3"
  :description "Useful tools for Clojure/ClojureScript"
  :url ""
  :license {}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/clojurescript "1.11.132"]
                 [net.cgrand/macrovich "0.2.1"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
