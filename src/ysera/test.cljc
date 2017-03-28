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
     (clojure.test/deftest ~name ~@body)))

(defmacro is [form]
  `(if-cljs
     (println "Not implemented.")
     (clojure.test/is ~form)))

(defmacro testing [string body]
  `(if-cljs
     (println "Not implemented.")
     (clojure.test/testing ~string ~body)))

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

(clojure.test/deftest
 similarity
 (clojure.test/is (= (macroexpand '(clojure.test/is (= 1 1)))
                     (macroexpand '(is (= 1 1)))))
 (clojure.test/is (= (macroexpand '(clojure.test/testing "foo" (= 1 1)))
                     (macroexpand '(testing "foo" (= 1 1)))))
 (clojure.test/is (= (macroexpand '(clojure.test/is (clojure.core/not (= 2 1))))
                     (macroexpand '(is-not (= 2 1)))))
 (clojure.test/is (= (macroexpand '(clojure.test/deftest
                                    name
                                    (clojure.core/is (= 1 1))
                                    (clojure.core/is (= 2 2))))
                     (macroexpand '(deftest
                                     name
                                     (clojure.core/is (= 1 1))
                                     (clojure.core/is (= 2 2)))))))

(clojure.test/deftest equals-1-1
  (is (= 1 1))
  (testing "that 1 equals 1" (= 1 1))
  (is= 1 1)
  (is-not (= 1 2)))
