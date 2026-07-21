# 📱 دليل التثبيت - AIChat Mobile

## الطريقة 1️⃣: باستخدام Sketchware (الأسهل)

### الخطوة 1: تحضير الملفات
```
1. انسخ ملف MainActivity.java
2. انسخ ملف activity_main.xml
3. انسخ ملف AndroidManifest.xml
4. انسخ ملفات الـ XML الأخرى
```

### الخطوة 2: إنشاء المشروع
```
1. افتح Sketchware
2. Create New Project
3. اختر Blank Project
4. سمه "AIChat"
```

### الخطوة 3: إضافة الواجهة
```
1. في Designer: أضف العناصر التالية:
   - EditText (input_message)
   - Button (send_button)
   - TextView (chat_display)
   - ScrollView (scroll_view)
```

### الخطوة 4: إضافة الكود
```
1. اذهب إلى Code tab
2. اضغط على Custom Code (البلوك الأخضر)
3. الصق كود MainActivity.java كاملاً
```

### الخطوة 5: إضافة الـ Permissions
```
1. في Manifest:
   - أضف: android.permission.INTERNET
```

### الخطوة 6: التشغيل
```
1. اضغط RUN
2. اختر جهازك أو المحاكي
3. انتظر التحميل
```

---

## الطريقة 2️⃣: باستخدام Android Studio

### المتطلبات:
- Android Studio مثبت
- JDK 11 أو أعلى
- Android SDK

### الخطوات:
```
1. استنسخ المستودع:
   git clone https://github.com/mwyaynnnmwyd-spec/AIChat-Mobile.git

2. افتح المجلد في Android Studio

3. دع Android Studio تحمل Dependencies

4. اضغط Run (أو Shift+F10)

5. اختر الجهاز
```

---

## ✅ التحقق من التثبيت

بعد التشغيل يجب أن تشاهد:
- ✅ شاشة الدردشة
- ✅ حقل إدخال النص
- ✅ زر الإرسال
- ✅ منطقة عرض الرسائل

---

## ❌ حل المشاكل الشائعة

### المشكلة: "خطأ في الاتصال"
**الحل:**
- تحقق من الإنترنت
- أعد تشغيل التطبيق
- تأكد من أن الخادم متصل

### المشكلة: "لا يظهر النص العربي بشكل صحيح"
**الحل:**
- تأكد من أن encoding هو UTF-8
- استخدم strings.xml للنصوص

### المشكلة: "البرنامج يتوقف"
**الحل:**
- تأكد من جميع الـ imports
- تحقق من أسماء العناصر (findViewById)
- أعد بناء المشروع (Clean and Rebuild)

---

## 🚀 بعد التثبيت بنجاح

1. **اكتب رسالة** في حقل الإدخال
2. **اضغط الزر** 📤
3. **انتظر الرد** من الذكاء الاصطناعي
4. **استمتع** بالدردشة! 😊

---

## 📞 للمساعدة

إذا واجهت أي مشاكل:
- تحقق من README.md
- شاهد الأخطاء في logcat
- اسأل في GitHub Issues
