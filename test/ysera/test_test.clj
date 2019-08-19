(ns ysera.test-test
  (:require [ysera.test]
            [ysera.error]
            [clojure.test]))


(clojure.test/deftest equals-1-1
  (ysera.test/is (= 1 1))
  (ysera.test/testing "that 1 equals 1" (= 1 1))
  (ysera.test/error? (ysera.error/error "an error!"))
  (ysera.test/is= 1 1)
  (ysera.test/is-not (= 1 2)))
