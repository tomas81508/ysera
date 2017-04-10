(ns ysera.test-test
  (:require [ysera.test]
            [clojure.test]))

(clojure.test/deftest
 similarity
 (clojure.test/is (= (macroexpand '(clojure.test/is (= 1 1)))
                     (macroexpand '(ysera.test/is (= 1 1)))))
 (clojure.test/is (= (macroexpand '(clojure.test/testing "foo" (= 1 1)))
                     (macroexpand '(ysera.test/testing "foo" (= 1 1)))))
 (clojure.test/is (= (macroexpand '(clojure.test/is (clojure.core/not (= 2 1))))
                     (macroexpand '(ysera.test/is-not (= 2 1)))))
 (clojure.test/is (= (macroexpand '(clojure.test/deftest
                                    name
                                    (clojure.core/is (= 1 1))
                                    (clojure.core/is (= 2 2))))
                     (macroexpand '(ysera.test/deftest
                                     name
                                     (clojure.core/is (= 1 1))
                                     (clojure.core/is (= 2 2)))))))

(clojure.test/deftest equals-1-1
  (ysera.test/is (= 1 1))
  (ysera.test/testing "that 1 equals 1" (= 1 1))
  (ysera.test/is= 1 1)
  (ysera.test/is-not (= 1 2)))
