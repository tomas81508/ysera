# ysera

Tools for Clojure development

### Why?

We like to write our programs in Clojure Common `.cljc` files. We dislike all the interop that needs to be coded in such files, we want abstractions instead that handles all the interop for us. This includes the interop needed for handling CLJS and CLJ ways of namespace importing, e.g. the clojure test library.

We also added the `is=` function because we suffered a lot from the optional `msg` argument in the default clojure `is` function. This lead to incorrectly typed test forms resulting in a passed test, like the following: `(is (= (* 2 2)) (+ 2 2)) -> true`.

### Documentation

We are strong believers that examples are excellent documentation. Our inline function tests serve as excellent examples, as they are guaranteed to be up to date. In the future we might generate this readme to include the examples here. In the mean time, have a look in our source files (they are not that big).

#### `ysera.collections`

* `index-of [coll x]`
* `remove-one [el coll]`
* `seq-contains? [coll x]`

#### `ysera.error`

* `error [message]`

#### `ysera.test`

* `deftest [name & body]`
* `error? [actual]`
* `is [form]`
* `is [form msg]`
* `is= [actual expected]`
* `is-not [actual]`
* `testing [string body]`
