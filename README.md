AnimatedPathView
================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AnimatedPathView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1317)


An animated path view following the methods in a blog post from Romain Guy here:<br>
http://www.curious-creature.org/2013/12/21/android-recipe-4-path-tracing/<br>
Per the blog post, I will be adding SVG support soon.

## Usage
You can set the path by calling:
```java
animatedPathView.setPath(Path p);
```

And you can set the percentage (0.0f to 1.0f) of the path being shown by calling:
```java
animatedPathView.setPercentage(float f);
```

You can also set the resize the path you created by calling:
```java
animatedPathView.scalePathBy(float x, float y);
```

<b>FYI</b>: As of right now, WRAP_CONTENT is disabled in order set paths relative to view with view.getWidth() and such.<br>
I will be fixing this in the future. So layout_width and layout_height must be specified!

Example: This will create a path that will draw a border around the top half of the screen
and an X through the middle of it.<br>

```xml
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res/com.mattkula.animatedpathview.sample"
    android:orientation="vertical" 
    android:layout_width="fill_parent"     
    android:layout_height="fill_parent">
    
    <com.mattkula.animatedpathview.library.AnimatedPathView 
        android:id="@+id/animated_path" 
        android:layout_width="fill_parent" 
        android:layout_height="0dp" 
        android:layout_weight="1" 
        app:strokeColor="@android:color/holo_red_light" 
        app:strokeWidth="10"/>
        
    <TextView 
        android:layout_width="fill_parent" 
        android:layout_height="0dp" 
        android:layout_weight="1" 
        android:text="Press top half of screen"/>
        
</LinearLayout>
```

```java
final AnimatedPathView view = (AnimatedPathView)findViewById(R.id.animated_path);

view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
    @Override
    public void onGlobalLayout() {
        view.getViewTreeObserver().removeGlobalOnLayoutListener(this);

        Path p = new Path();
        p.moveTo(0, 0);
        p.lineTo(view.getWidth(), 0);
        p.lineTo(view.getWidth(), view.getHeight());
        p.lineTo(0, view.getHeight());
        p.lineTo(0, 0);
        p.lineTo(view.getWidth(), view.getHeight());
        p.lineTo(view.getWidth(), 0);
        p.lineTo(0, view.getHeight());
        view.setPath(p);
    }
});

view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "percentage", 0.0f, 1.0f);
        anim.setDuration(2000);
        anim.setInterpolator(new LinearInterpolator());
        anim.start();
    }
}); 
```

If you just want to use straight lines, you can use an array of points to specify your path such as:
```java
float[][] points = new float[][]{
        {0, 0},
        {view.getWidth(), 0},
        {view.getWidth(), view.getHeight()},
        {0, view.getHeight()},
        {0, 0},
        {view.getWidth(), view.getHeight()},
        {view.getWidth(), 0},
        {0, view.getHeight()}
};
view.setPath(points);
```
