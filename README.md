# MyWear
<p>send data from smart phone to android wear</p>
<p>1-connect smartphone with android wear emulator</p>
<p>install Android Wear from Google play in the smartphone :
<a href="https://play.google.com/store/apps/details?id=com.google.android.wearable.app&hl=en">
https://play.google.com/store/apps/details?id=com.google.android.wearable.app&amp;hl=en</a></p>
<p>activate debug mode in the smart phone, and connect it with usb to the 
computer.</p>
<p>lunch android wear emulator.</p>
<p>start connecting emulator from the smartphone.</p>
<p>tap in cmd commande <span style="background-color: #c0c0c0;"><code>&lt;ANDROID_SDK&gt;/plateform-tools/adb 
-d forward tcp:5601 tcp:5601</code></span></p>
<p>&lt;ANDROID_SDK&gt; is the android sdk path.</p>
<p>
<img border="0" src="https://github.com/AymenSoft/MyWear/blob/master/screenshots/1.PNG"></p>
<p>
<img border="0" src="https://github.com/AymenSoft/MyWear/blob/master/screenshots/3.PNG">
</p>
<p>2-configure android project</p>
<p>in the project gradle add:</p>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">classpath <span style="color:#6a8759;">'me.tatarka:gradle-retrolambda:3.3.1'

</span>classpath <span style="color:#6a8759;">'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.1'
</span>classpath <span style="color:#6a8759;">&quot;com.github.dcendents:android-maven-gradle-plugin:1.5&quot;

</span>classpath <span style="color:#6a8759;">'com.dicedmelon.gradle:jacoco-android:0.1.1'</span></pre>
<p>import library module to the project</p>
<p>in mobile module gradle add:</p>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">apply <span style="color:#6a8759;">plugin</span>: <span style="color:#6a8759;">'me.tatarka.retrolambda'</span></pre>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">compileOptions {
    <span style="color:#9876aa;">sourceCompatibility </span>JavaVersion.<span style="color:#9876aa;font-style:italic;">VERSION_1_8
    </span><span style="color:#9876aa;">targetCompatibility </span>JavaVersion.<span style="color:#9876aa;font-style:italic;">VERSION_1_8
</span>}
lintOptions {
    <span style="color:#9876aa;">abortOnError </span><span style="color:#cc7832;">false
</span>}</pre>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">retrolambda {
    javaVersion JavaVersion.<span style="color:#9876aa;font-style:italic;">VERSION_1_6
</span>}</pre>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">compile project(<span style="color:#6a8759;">':library'</span>)<span style="color:#808080;">

</span>compile <span style="color:#6a8759;">'io.reactivex:rxjava:1.2.1'
</span>compile <span style="color:#6a8759;">'io.reactivex:rxandroid:1.2.1'
</span>compile <span style="color:#6a8759;">'com.jakewharton.rxbinding:rxbinding:0.4.0'</span></pre>
<p>in wear module gradle add:</p>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">apply <span style="color:#6a8759;">plugin</span>: <span style="color:#6a8759;">'me.tatarka.retrolambda'</span></pre>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">compileOptions {
    <span style="color:#9876aa;">sourceCompatibility </span>JavaVersion.<span style="color:#9876aa;font-style:italic;">VERSION_1_8
    </span><span style="color:#9876aa;">targetCompatibility </span>JavaVersion.<span style="color:#9876aa;font-style:italic;">VERSION_1_8
</span>}
lintOptions {
    <span style="color:#9876aa;">abortOnError </span><span style="color:#cc7832;">false
</span>}</pre>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">retrolambda {
    javaVersion JavaVersion.<span style="color:#9876aa;font-style:italic;">VERSION_1_6
    </span>jvmArgs <span style="color:#6a8759;">'-noverify'
</span>}</pre>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;">compile project(<span style="color:#6a8759;">':library'</span>)
<span style="color:#808080;">//compile 'com.patloew.rxwear:rxwear:1.3.0'

</span>compile <span style="color:#6a8759;">'io.reactivex:rxjava:1.2.1'
</span>compile <span style="color:#6a8759;">'io.reactivex:rxandroid:1.2.1'
</span>compile <span style="color:#6a8759;">'com.jakewharton.rxbinding:rxbinding:0.4.0'</span></pre>
<p>to send a message from the smart phone use :</p>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;"><span style="background-color:#344134;">rxWear</span>.message().sendDataMapToAllRemoteNodes(<span style="color:#6a8759;">&quot;/message&quot;</span>)
        .putString(<span style="color:#6a8759;">&quot;title&quot;</span><span style="color:#cc7832;">, </span><span style="color:#9876aa;">etmsg</span>.getText().toString())
        .putString(<span style="color:#6a8759;">&quot;message&quot;</span><span style="color:#cc7832;">, </span><span style="color:#6a8759;">&quot;My message&quot;</span>)
        .toObservable()
        .subscribe(requestId -&gt; Snackbar.<span style="font-style:italic;">make</span>(<span style="color:#9876aa;">llmain</span><span style="color:#cc7832;">, </span><span style="color:#6a8759;">&quot;Sent message&quot;</span><span style="color:#cc7832;">, </span>Snackbar.<span style="color:#9876aa;font-style:italic;">LENGTH_LONG</span>).show()<span style="color:#cc7832;">,
                </span>throwable -&gt; {
                    Log.<span style="font-style:italic;">e</span>(<span style="color:#6a8759;">&quot;MainActivity&quot;</span><span style="color:#cc7832;">, </span><span style="color:#6a8759;">&quot;Error on sending message&quot;</span><span style="color:#cc7832;">, </span>throwable)<span style="color:#cc7832;">;

                    if</span>(throwable <span style="color:#cc7832;">instanceof </span>GoogleAPIConnectionException) {
                        Snackbar.<span style="font-style:italic;">make</span>(<span style="color:#9876aa;">llmain</span><span style="color:#cc7832;">, </span><span style="color:#6a8759;">&quot;Android Wear app is not installed&quot;</span><span style="color:#cc7832;">, </span>Snackbar.<span style="color:#9876aa;font-style:italic;">LENGTH_LONG</span>).show()<span style="color:#cc7832;">;
                    </span>} <span style="color:#cc7832;">else </span>{
                        Snackbar.<span style="font-style:italic;">make</span>(<span style="color:#9876aa;">llmain</span><span style="color:#cc7832;">, </span><span style="color:#6a8759;">&quot;Could not send message&quot;</span><span style="color:#cc7832;">, </span>Snackbar.<span style="color:#9876aa;font-style:italic;">LENGTH_LONG</span>).show()<span style="color:#cc7832;">;
                    </span>}
                })<span style="color:#cc7832;">;</span></pre>
<p>add a listner in the wear to read the message:</p>
<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Courier New';font-size:12,0pt;"><span style="background-color:#344134;">subscription</span>.add(<span style="color:#9876aa;">rxWear</span>.message().listen(<span style="color:#6a8759;">&quot;/message&quot;</span><span style="color:#cc7832;">, </span>MessageApi.<span style="color:#9876aa;font-style:italic;">FILTER_LITERAL</span>)
        .compose(MessageEventGetDataMap.<span style="font-style:italic;">noFilter</span>())
        .subscribe(dataMap -&gt; <span style="color:#9876aa;">mTextView</span>.setText(dataMap.getString(<span style="color:#6a8759;">&quot;title&quot;</span><span style="color:#cc7832;">, </span><span style="color:#6a8759;">&quot;no msg&quot;</span>))<span style="color:#cc7832;">, </span>throwable -&gt; Toast.<span style="font-style:italic;">makeText</span>(<span style="color:#cc7832;">this, </span><span style="color:#6a8759;">&quot;Error on message listen&quot;</span><span style="color:#cc7832;">, </span>Toast.<span style="color:#9876aa;font-style:italic;">LENGTH_LONG</span>).show()))<span style="color:#cc7832;">;</span></pre>
<p>and the result will be something like that:</p>
<p>
<img border="0" src="https://github.com/AymenSoft/MyWear/blob/master/screenshots/2.PNG"></p>
<p>Enjoy Developping with simple trics from AymenSoft<br>
WebSite: <a href="http://www.aymen-soft.com">www.aymen-soft.com</a><br>
Facebook: <a href="https://www.facebook.com/AymenSoft">
https://www.facebook.com/AymenSoft</a><br>
twitter: <a href="https://twitter.com/aymen_soft">https://twitter.com/aymen_soft</a><br>
GooglePlus: <a href="https://plus.google.com/+AymenSoftOfficial">
https://plus.google.com/+AymenSoftOfficial</a><br>
Telegram: <a href="https://telegram.me/AymenSoftOfficial">
https://telegram.me/AymenSoftOfficial</a></p>
<p>&nbsp;</p>
