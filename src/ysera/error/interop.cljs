(ns ysera.error.interop)

(defn error [message]
      (throw (js/Error message)))

