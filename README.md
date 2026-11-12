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

## Build script

`./build.sh` builds the debug APK and runs the application's JVM unit tests.
It fetches only the pinned library closure before invoking this checkout's Gradle wrapper.
Additional Gradle arguments are forwarded unchanged, for example `--max-workers=1` or `-PtargetAbi=arm64-v8a`.

## Fresh checkout build

Install JDK 17 or 21, Python 3, Android SDK platform 36, build tools 35.0.0,
NDK 27.2.12479018 and CMake 3.22.1. Set `ANDROID_HOME`, or set `sdk.dir` in
this checkout's untracked `local.properties`.

```sh
git clone https://github.com/gretchycat/infiniscribe.git
cd infiniscribe
./build.sh --max-workers=1 --console=plain
```

The APK is `build/outputs/apk/debug/InfiniScribe-debug.apk`. Add
`-PtargetAbi=arm64-v8a` to restrict application packaging. Gradle 9.7.1 and its
checksum are pinned in the wrapper. The complete source-dependency closure is
pinned in `dependencies.lock.json`; the bootstrap fetches only those libraries
inside `libraries/`. No parent or sibling checkout is consulted.

On Termux, install JDK, Python, clang and aapt2. The build script configures native aapt2 automatically; direct Gradle calls need
`android.aapt2FromMavenOverride` set to the installed executable.
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

## Build environment discovery

`./build.sh` honors `ANDROID_HOME`, `ANDROID_SDK_ROOT` or this checkout's `local.properties`. With none configured, it discovers an installed SDK in `~/android-sdk`, `~/Android/Sdk` or `~/Library/Android/sdk`, and reports a clear error if absent. It does not install an SDK or write local configuration. On Termux it passes the installed native `aapt2` explicitly and defaults arm64 packaging to `arm64-v8a`; explicit Gradle arguments can override that default. Direct `./gradlew` invocation still requires SDK/tool configuration.

Run `python3 scripts/test-android-build-env.py` for the build entry point's isolated regression checks.

## Android verification checkpoint

On 2026-11-11, implementation commit `9f0ee73` produced its debug APK and passed 17 unit tests in the user's independent Termux checkout. `./build.sh --max-workers=1 --console=plain` ran with SDK environment variables unset and no local.properties, plus a guard rejecting project/source roots outside that checkout. The 11 build-environment regression checks also passed. APK inspection confirmed no media-player application or advanced Folder storage implementation. Device rendering/grants/lifecycle validation remains separate; Ubuntu testing and other platform ports are deferred.

## Shared image rendering update

The pinned Canvas decoder supports Infini SVG palette-cycling metadata directly, alongside existing indexed IFF animation. Its scheduler is now shared by UI image presentation and skin artwork; Scribe still does not include the Media application or its theme assets.

The shared rendering update at a51efa5 passed all 17 app tests and 11 build-environment checks in the independent checkout using build.sh without SDK variables or local.properties, with the source-root guard and library pin verification enabled. The debug APK was produced successfully. This verifies compilation/package compatibility; visible image animation remains a device acceptance check.
