package network.util

import androidx.annotation.NonNull
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/*

@created in 27/04/2020 - 6:46 PM
@project Retrofit_startup2
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


@Retention(AnnotationRetention.RUNTIME)
annotation class CSampleXml()

@Retention(AnnotationRetention.RUNTIME)
annotation class CGson()

@Retention(AnnotationRetention.RUNTIME)
annotation class CScalarPlain()


class CConverters {

    companion object {
        class Builder {
            private var factories = hashMapOf<Class<out Annotation> , Converter.Factory>()

            fun addFullFactorys(factories: HashMap<Class<out Annotation> , Converter.Factory>){
                this.factories =  factories
            }

            fun add(@NonNull clazz: Class<out Annotation> ,@NonNull factory: Converter.Factory) = this.apply {
                factories.put(clazz , factory)
            }

            fun build(): ModelConverter {
                if (factories.size == 0){
                    throw IllegalArgumentException("minimum converters must is 1")
                }
                return ModelConverter(
                    factories
                )
            }
        }

        class ModelConverter internal constructor(
            private val factories: HashMap<Class<out Annotation> , Converter.Factory>
        ) : Converter.Factory() {

            override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? =
                getConverter(annotations).responseBodyConverter(type , annotations , retrofit)


            override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? =
                getConverter(methodAnnotations).requestBodyConverter(type , parameterAnnotations , methodAnnotations , retrofit)


                private fun getConverter(annotations: Array<Annotation>): Converter.Factory {
                    if (annotations.size == 0){
                        throw IllegalArgumentException("Your annotations is null")
                    }

                    val validateAnnot = mutableListOf<Converter.Factory>()
                    for (annotation in annotations) {
                        val result = factories.get(annotation.annotationClass.javaObjectType)
                        if (result != null){
                            validateAnnot.add(result)
                        }
                    }

                    if (validateAnnot.size != 1){
                        throw IllegalArgumentException("minimun and maximum your converter must be 1 in one method")
                    }

                    return validateAnnot[0]
                }

            }
    }
}

