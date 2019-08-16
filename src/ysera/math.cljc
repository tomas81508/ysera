(ns ysera.math
  (:require [ysera.test #?(:clj :refer :cljs :refer-macros) [is=]]))

(defn round
  {:test (fn []
           (is= (round 2.4)
                2)
           (is= (round 2.5)
                3))}
  [n]
  #?(:clj  (Math/round (double n))
     :cljs (js/Math.round n)))

(defn floor
  {:test (fn []
           (is= (floor 2.4)
                2)
           (is= (floor 2.5)
                2))}
  [x]
  #?(:clj  (int (Math/floor (double x)))
     :cljs (js/Math.floor x)))

(defn ceil
  {:test (fn []
           (is= (ceil 2.4)
                3)
           (is= (ceil 2.5)
                3))}
  [x]
  #?(:clj  (int (Math/ceil (double x)))
     :cljs (js/Math.ceil x)))

(def PI #?(:clj  (Math/PI)
           :cljs (js/Math.PI)))

(defn sin
  {:test (fn []
           (is= (sin (/ PI 2)) 1.0))}
  [x]
  #?(:clj  (Math/sin (double x))
     :cljs (js/Math.sin x)))

(defn cos
  {:test (fn []
           (is= (cos PI) -1.0))}
  [x]
  #?(:clj  (Math/cos (double x))
     :cljs (js/Math.cos x)))