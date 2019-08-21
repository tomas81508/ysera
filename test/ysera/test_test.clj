(ns ysera.test-test
  (:require [clojure.test]
            [ysera.test]
            [ysera.error]))

(comment
  (clojure.test/deftest equals-1-1
    (ysera.test/is (= 1 1))
    (ysera.test/testing "that 1 equals 1" (= 1 1))
    (ysera.test/error? (ysera.error/error "an error!"))
    (ysera.test/is= 1 1)
    (ysera.test/is-not (= 1 2))))
