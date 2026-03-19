# 💊 DrugBank Explorer — Android App
**Klgo. Matías Carreño Román · UC CHRISTUS · v2.2**

---

## Compilar el APK

### Opción 1 — Android Studio (más fácil)

1. **Descargar Android Studio** → https://developer.android.com/studio
2. **Abrir proyecto**: File → Open → seleccionar esta carpeta
3. Esperar sincronización de Gradle (descarga dependencias ~5 min primera vez)
4. Menú **Build → Build Bundle(s) / APK(s) → Build APK(s)**
5. Clic en **"locate"** cuando aparezca la notificación
6. APK en: `app/build/outputs/apk/debug/app-debug.apk`

### Opción 2 — GitHub Actions (en la nube, sin instalar nada)

1. Subir esta carpeta a un repositorio GitHub
2. Ir a **Actions** → **Build DrugBank APK** → **Run workflow**
3. Descargar el APK desde los **Artifacts** al terminar

### Opción 3 — Línea de comandos

```bash
# Requiere Android SDK (ANDROID_HOME configurado)
chmod +x ./gradlew
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

---

## Instalar en Android

1. Copiar el APK al teléfono (USB / WhatsApp / Drive / email)
2. En el teléfono: **Ajustes → Seguridad → Instalar apps desconocidas → Permitir**
3. Abrir el APK con el gestor de archivos
4. Confirmar instalación

Compatibilidad: **Android 7.0+ (API 24)**

---

## Funciones

| Pestaña | Descripción |
|---|---|
| 🔍 Búsqueda | Busca cualquier fármaco · datos desde PubChem · RxNorm · OpenFDA |
| ⚡ Interacciones | Verifica hasta 5 fármacos simultáneamente |

### Verificador de Interacciones
- Ingresa 2–5 fármacos
- Clasificación por severidad: **Mayor · Moderada · Menor**
- Manejo clínico para cada par
- Base curada: warfarina, AINEs, tramadol, estatinas, IBPs, antidepresivos, antibióticos…

### Búsqueda de fármacos (7 pestañas)
- 📋 General: descripción, indicaciones, sinónimos, marcas, ATC
- ⚗️ Farmacología: mecanismo, metabolismo, absorción, eliminación
- 🔬 Molecular: peso molecular, logP, TPSA, SMILES, InChI
- ⚠️ Seguridad: toxicidad, contraindicaciones, interacciones
- 🧬 Blancos: targets moleculares, enzimas, transportadores
- 💊 Dosificación: formas farmacéuticas, vías (FDA NDC)
- 🔗 IDs: PubChem, RxCUI, InChIKey

---

## Arquitectura

```
Android WebView
    └── assets/index.html (app completa ~70KB, HTML+CSS+JS)
            ├── Búsqueda → PubChem API (NIH)
            │            → RxNorm API (NIH)
            │            → OpenFDA API (FDA)
            └── Interacciones → Base de datos curada (local, offline)
                              → OpenFDA drug interactions (online)
```

**Sin API Key · Sin servidor · Sin costos · Datos NIH/FDA oficiales**

---

*DrugBank Explorer v2.2 · Klgo. Matías Carreño Román · UC CHRISTUS · Santiago de Chile*
