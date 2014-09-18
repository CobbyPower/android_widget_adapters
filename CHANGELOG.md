Change-Log
===============

> **RELEASE VERSION** (<i>RELEASE DATE</i>)

### **2.3.1** (<i>18.09.2014</i>) ###
- Fixed BaseAdapter where notifyDataSetInvalidated() wrongly called super.notifyDataSetChanged().

### **2.3** (<i>11.09.2014</i>) ###
- @ItemViewHolder, @DropDownViewHolder annotations are **deprecated** because of overhead during
instantiation of holders using reflection.
- Added @ItemViewHolderFactory + @DropDownViewHolderFactory annotations instead deprecated ones +
additional interfaces to support new instantiation process of holders for adapters.

### **2.2** (<i>27.08.2014</i>) ###
- @ItemView, @ItemViewHolder, @DropDownView, @DropDownViewHolder annotations are processed recursively, 
so a subclass of annotated adapter class will also inherit its annotations.

### **2.1** (<i>25.08.2014</i>) ###
- Updated classes structure due to libraries global refactoring.
- Added new constructors for SimpleAdapter and SimpleSpinnerAdapter for initial List/Array with also 
changeItems(List/Array) method.