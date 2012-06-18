Official Bugsnag Notifier for Android
=====================================


Overview
--------

The Bugsnag Notifier for Android is designed to give you
instant notification of exceptions thrown from your Android applications. 
The notifier hooks into `Thread.UncaughtExceptionHandler`, which means any 
uncaught exceptions will trigger a notification to be sent to your Bugsnag
project.


What is Bugsnag?
----------------

[Bugsnag](http://bugsnag.com) captures errors in real-time from your web, 
mobile and desktop applications, helping you to understand and resolve them 
as fast as possible. [Create a free account](http://bugsnag.com).


Features
--------

-   Automatic notification of uncaught exceptions
-   Exceptions buffered to disk when no internet connection is available, and 
    sent later
-   Minimal cpu and memory footprint
-   Send your own [non-fatal exceptions](#send-non-fatal-exceptions-to-bugsnag)
    to Bugsnag
-   Track which activities were open at the time of the exception using the 
    [`BugsnagActivity`](#recommended-inherit-from-bugsnagactivity) class
-   Send additional meta-data along with your exceptions using 
    [`setExtraData`](#setextradata)
-   Filter sensitive data before sending exceptions with
    [`setFilters`](#setfilters)


Installation & Setup
--------------------

[Download the latest bugsnag.jar file](https://github.com/downloads/bugsnag/bugsnag-android/bugsnag-1.0.0.jar)
and place it in your Android app's `libs/` folder.

Import the `Bugsnag` package in your Activity.

```java
import com.bugsnag.android.*;
```

In your first activity's `onCreate` function, register to begin capturing 
exceptions:

```java
Bugsnag.register(this, "your-api-key-goes-here");
```


Recommended: Inherit from BugsnagActivity
-----------------------------------------

To have additional useful data about exceptions sent to Bugsnag, you should
also have each of your `Activity` classes inherit from `BugsnagActivity`.
This will track which of your activities were open at the time of
any exception, and present them in your Bugsnag error dashboard.

```java
class MyActivity extends BugsnagActivity {
    ...
}
```


Send Non-Fatal Exceptions to Bugsnag
------------------------------------

If you would like to send non-fatal exceptions to Bugsnag, you can pass any
`Throwable` onject to the `notify` method:

```java
Bugsnag.notify(new RuntimeException("Non-fatal"));
```

You can also send additional meta-data with your exception:

```java
Map<String,String> extraData = new HashMap<String,String>();
extraData.put("username", "bob-hoskins");
extraData.put("registered_user", "yes");

Bugsnag.notify(new RuntimeException("Non-fatal"), extraData);
```


Configuration
-------------

###setContext

Bugsnag uses the concept of "contexts" to help display and group your
errors. Contexts represent what was happening in your application at the
time an error occurs. In an android app, it is useful to set this to be 
your currently active `Activity`.
    
If you are using `BugsnagActivity` then this is set automatically for you.
If you would like to set the bugsnag context manually, you can call 
`setContext`:

```java
Bugsnag.setContext("MyActivity");
```

###setUserId

Bugsnag helps you understand how many of your users are affected by each
error. In order to do this, we send along a userId with every exception. 
By default we will generate a unique ID and send this ID along with every 
exception from an individual device.
    
If you would like to override this `userId`, for example to set it to be a
username of your currently logged in user, you can call `setUserId`:

```java
Bugsnag.setUserId("leeroy-jenkins");
```

###setReleaseStage

If you would like to distinguish between errors that happen in different
stages of the application release process (development, production, etc)
you can set the `releaseStage` that is reported to Bugsnag.

```java
Bugsnag.setReleaseStage("development");
```
    
By default this is set to be "production".

###setNotifyReleaseStages

By default, we will only notify Bugsnag of exceptions that happen when 
your `releaseStage` is set to be "production". If you would like to 
change which release stages notify Bugsnag of exceptions you can
call `setNotifyReleaseStages`:
    
```java
Bugsnag.setNotifyReleaseStages(new String[]{"production", "development"});
```

###setAutoNotify

By default, we will automatically notify Bugsnag of any fatal exceptions
in your application. If you want to stop this from happening, you can call
`setAutoNotify`:
    
```java
Bugsnag.setAutoNotify(false);
```

###setExtraData

It if often very useful to send some extra application or user specific 
data along with every exception. To do this, you can call the
`setExtraData` method:
    
```java
Map<String,String> extraData = new HashMap<String,String>();
extraData.put("username", "bob-hoskins");
extraData.put("registered_user", "yes");

Bugsnag.setExtraData(extraData);
```

###setFilters

Sets the strings to filter out from the `extraData` maps before sending
them to Bugsnag. Use this if you want to ensure you don't send 
sensitive data such as passwords, and credit card numbers to our 
servers. Any keys which contain these strings will be filtered.

```java
Bugsnag.setFilters(new String[]{"password", "credit_card_number"});
```

By default, `filters` is set to `new String[] {"password"};`


Building from Source
--------------------

To build a `.jar` file from source, clone the bugsnag-android repository 
and run:

```bash
ant package
```

This will generate a file named `bugsnag.jar`.


Reporting Bugs or Feature Requests
----------------------------------

Please report any bugs or feature requests on the github issues page for this
project here:

<https://github.com/bugsnag/bugsnag-android/issues>


Contributing
------------
 
* Check out the latest master to make sure the feature hasn't been implemented or the bug hasn't been fixed yet
* Check out the issue tracker to make sure someone already hasn't requested it and/or contributed it
* Fork the project
* Start a feature/bugfix branch
* Commit and push until you are happy with your contribution
* Thanks!


License
-------

The Bugsnag android notifier is released under the Android-friendly
Apache License, Version 2.0. Read the full license here:

<http://www.apache.org/licenses/LICENSE-2.0>