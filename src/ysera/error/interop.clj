(ns ysera.error.interop)

(defn error [message]
  (throw (Exception. message)))