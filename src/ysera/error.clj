(ns ysera.error)

(defn error [message]
  (throw (Exception. message)))
