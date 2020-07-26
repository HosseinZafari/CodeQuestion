package hosseinzafari.github.framework.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import hosseinzafari.github.framework.core.app.G
import org.xmlpull.v1.XmlPullParser

/*
@created in 25/06/2020 - 12:01 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

// Extensions for LayoutInflaters With ViewGroup Class.

fun ViewGroup.inflate(
    @LayoutRes layoutRes: Int ,
    attachToRoot: Boolean = false
) = G.layoutInflater.inflate(layoutRes , this , attachToRoot)

fun ViewGroup.inflateMaterial(
    @LayoutRes layoutRes: Int ,
    attachToRoot: Boolean = false
) : View {
    val inflater = G.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return inflater.inflate(layoutRes , this , attachToRoot)
}

fun ViewGroup.inflate(
    xmlPullParser: XmlPullParser ,
    attachToRoot: Boolean = false
) = G.layoutInflater.inflate(xmlPullParser , this , attachToRoot)

// Extensions for LayoutInflaters Without ViewGroup Class.
fun layoutInflate(
    @LayoutRes layoutRes: Int
) = G.layoutInflater.inflate(layoutRes ,  null )

