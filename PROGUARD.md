Proguard
===============

This file describes, which proguard rules **should** be used to preserve *proper working* of the
source code of this library project when a proguard process is applied to a project which uses this 
library.

### Proguard-Rules ###

> Use below rules to **obfuscate as much** source code of this library project **as possible**.

    # Keep all annotations.
    -keep public @interface *
    # Keep adapter item view holders which are instantiated using reflection.
    -keep public class * implements com.wit.android.ui.widget.adapter.ViewHolder

> Use below rules to **not obfuscate** any source code of this library project.

    # Keep all classes within library package.
    -keep class com.wit.android.ui.widget.adapter.** { *; }