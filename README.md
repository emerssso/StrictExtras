# StrictExtras

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1fc8766a4e3444a5919af6b1901c2201)](https://app.codacy.com/app/emerssso/StrictExtras?utm_source=github.com&utm_medium=referral&utm_content=emerssso/StrictExtras&utm_campaign=badger)
[![Build Status](https://travis-ci.org/emerssso/StrictExtras.svg?branch=master)](https://travis-ci.org/emerssso/StrictExtras)

This project demonstrates how to use Kotlin to enable strict/statically typed extras/arguments
for your Activities, Services, and Fragments. To start an Activity using this pattern
you simply call `context.startActivityWith(MyActivity.Extras("param1", param2))`.

On the Activity side, these extras are available via extension functions, i.e. 
`activity.extras().param2`.

This is all achieved with a few interfaces and some reified generics. In order to support this API,
simply create a data class that implements the `ActivityExtras` interface:

    @Parcelize
    data class Extras(val param1: String, val param2: Int) : ActivityExtras<MyActivty>
    
Activities and Fragments may optionally implement `StrictActivityExtras` or 
`StrictFragmentArguments` but this really only allows you to avoid a cast when calling `extras()`.

Check the `app` module for example usages. The `strictextras` module contains a library with
the APIs. Library availability via Maven forthcoming.

