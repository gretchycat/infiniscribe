# InfiniScribe

Independent Android document viewer/editor and ebook reader. Application ID:
`com.infiniscribe`; Android 8.0 (API 26) or newer. Supports text and syntax-aware
editing, Markdown/HTML, CSV/workbooks, PDF, EPUB and other supported document legacy MindNode Touch mind maps, and
font formats through the optional backends in libinfiniscribe. The shared image
viewer is supplied by libinfiniui; document UI lives in this application's modules.

External files are selected through libinfiniselect and opened as Android content
references. Infinifolder supplies advanced remote/container storage when installed.
Android SAF remains functional without it. No remote clients, torrent engine,
mounted filesystems, libinfinistore or libinfinivault are bundled in this app.
Standard ZIP expansion needed by document formats belongs to document processing.

## Fresh checkout build

Install JDK 17 or 21, Python 3, Android SDK platform 36, build tools 35.0.0,
NDK 27.2.12479018 and CMake 3.22.1. Set `ANDROID_HOME`, or set `sdk.dir` in
this checkout's untracked `local.properties`.

```sh
git clone https://github.com/gretchycat/infiniscribe.git
cd infiniscribe
python3 scripts/fetch-dependencies.py
./gradlew assembleDebug testDebugUnitTest
```

The APK is `build/outputs/apk/debug/InfiniScribe-debug.apk`. Add
`-PtargetAbi=arm64-v8a` to restrict application packaging. Gradle 9.7.1 and its
checksum are pinned in the wrapper. The complete source-dependency closure is
pinned in `dependencies.lock.json`; the bootstrap fetches only those libraries
inside `libraries/`. No parent or sibling checkout is consulted.

On Termux, install JDK, Python, clang and aapt2. Configure the supported Gradle
`android.aapt2FromMavenOverride` property with the installed executable's path.
The MOBI JNI bridge uses the Termux toolchain there and the pinned NDK elsewhere.

Shared UI/theme/icon code has one source owner in libinfiniui. The document library
contains processing backends; libinfinisound supplies the existing soundbank text
inspection used by the text viewer. It does not add the media-player application
or Android playback module. Every dependency's responsibility and public interface
are documented in its own repository.

Unit tests and compilation do not establish Android grant, rendering or lifecycle
correctness on a device. See Infinifolder's architecture audit for executed checks
and the remaining runtime validation matrix. Original-code licensing is pending;
see LICENSE.md. Preserve bundled third-party notices when distributing the APK.
