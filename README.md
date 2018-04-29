# StrictExtras

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

