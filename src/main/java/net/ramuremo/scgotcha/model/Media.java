package net.ramuremo.scgotcha.model;

public interface Media {
    public static String a = """
"transcodings": [
  {
    "url": "https://api-v2.soundcloud.com/media/soundcloud:tracks:1132864861/4cd7578a-eb49-44aa-b966-4782ff1e61d8/stream/hls",
    "preset": "mp3_0_1",
    "duration": 101851,
    "snipped": false,
    "format": {
      "protocol": "hls",
      "mime_type": "audio/mpeg"
    },
    "quality": "sq"
  },
  {
    "url": "https://api-v2.soundcloud.com/media/soundcloud:tracks:1132864861/4cd7578a-eb49-44aa-b966-4782ff1e61d8/stream/progressive",
    "preset": "mp3_0_1",
    "duration": 101851,
    "snipped": false,
    "format": {
      "protocol": "progressive",
      "mime_type": "audio/mpeg"
    },
    "quality": "sq"
  },
  {
    "url": "https://api-v2.soundcloud.com/media/soundcloud:tracks:1132864861/a2a7828a-24f4-4693-b743-5b7f212c799c/stream/hls",
    "preset": "opus_0_0",
    "duration": 101832,
    "snipped": false,
    "format": {
      "protocol": "hls",
      "mime_type": "audio/ogg; codecs=\\"opus\\""
    },
    "quality": "sq"
  }
]
""";
}
