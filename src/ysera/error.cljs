(ns ysera.error)

(defn error [message]
  (throw (js/Error message)))
