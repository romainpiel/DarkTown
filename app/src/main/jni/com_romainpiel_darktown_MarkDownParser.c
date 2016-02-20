#include "com_romainpiel_darktown_MarkDownParser.h"
#include "libhoedown/document.h"
#include "libhoedown/html.h"

#define INPUT_UNIT 64
#define OUTPUT_UNIT 64

JNIEXPORT jstring JNICALL Java_com_romainpiel_darktown_MarkDownParser_markdownToHtml
  (JNIEnv *env, jobject o, jstring raw) {
  struct hoedown_buffer *ib, *ob;
  jstring result;
  hoedown_renderer *renderer;
  hoedown_document *document;
  const char* str;

  str = (*env)->GetStringUTFChars(env, raw, NULL);

  ib = hoedown_buffer_new(INPUT_UNIT);
  hoedown_buffer_puts(ib, str);
  ob = hoedown_buffer_new(OUTPUT_UNIT);

  (*env)->ReleaseStringUTFChars(env, raw, str);

  renderer = hoedown_html_renderer_new(0, 0);
  document = hoedown_document_new(renderer, 0, 16);

  hoedown_document_render(document, ob, ib->data, ib->size);
  hoedown_document_free(document);

  result=(*env)->NewStringUTF(env, hoedown_buffer_cstr(ob));

  /* cleanup */
  hoedown_buffer_free(ib);
  hoedown_buffer_free(ob);

  return(result);
}
