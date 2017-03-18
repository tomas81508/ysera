(ns ysera.test
  #?(:clj (:require [clojure.test])))

(defn cljs-env?
  "Take the &env from a macro, and tell whether we are expanding into cljs."
  [env]
  (boolean (:ns env)))

(defmacro if-cljs
  "Return then if we are generating cljs code and else for Clojure code.
   https://groups.google.com/d/msg/clojurescript/iBY5HaQda4A/w1lAQi9_AwsJ"
  [then else]
  (if (cljs-env? &env) then else))

(defmacro deftest [name & body]
  `(if-cljs
     (println "Not implemented.")
     (concat (list 'clojure.test/deftest name) body)))

(defmacro is [form]
  `(if-cljs
     (println "Not implemented.")
     (list 'clojure.test/is 'form)))

(defmacro testing [string body]
  `(if-cljs
     (println "Not implemented.")
     (list 'clojure.test/testing string body)))

(defmacro is= [actual expected]
  `(if-cljs
     (println "Not implemented.")
     (let [actual# ~actual
           expected# ~expected
           equal# (= actual# expected#)]
       (do
         (when-not equal#
           (println "Actual:\t\t" actual# "\nExpected:\t" expected#))
         (clojure.test/is (= actual# expected#))))))

(defmacro is-not [actual]
  `(if-cljs
     (println "Not implemented.")
     (clojure.test/is (not ~actual))))

(defmacro error? [actual]
  `(if-cljs
     (println "Not implemented.")
     (try (do
            ~actual
            (println "An error was expected.")
            (clojure.test/is false))
          (catch Exception e#
            (clojure.test/is true)))))
