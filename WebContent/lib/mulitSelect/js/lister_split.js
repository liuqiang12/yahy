var BrowserDetect = {
    init: function() {
        this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS"
    },
    searchString: function(d) {
        for (var a = 0; a < d.length; a++) {
            var b = d[a].string;
            var c = d[a].prop;
            this.versionSearchString = d[a].versionSearch || d[a].identity;
            if (b) {
                if (b.indexOf(d[a].subString) != -1) {
                    return d[a].identity
                }
            } else {
                if (c) {
                    return d[a].identity
                }
            }
        }
    },
    searchVersion: function(b) {
        var a = b.indexOf(this.versionSearchString);
        if (a == -1) {
            return
        }
        return parseFloat(b.substring(a + this.versionSearchString.length + 1))
    },
    dataBrowser: [{
        string: navigator.userAgent,
        subString: "Chrome",
        identity: "Chrome"
    }, {
        string: navigator.userAgent,
        subString: "OmniWeb",
        versionSearch: "OmniWeb/",
        identity: "OmniWeb"
    }, {
        string: navigator.vendor,
        subString: "Apple",
        identity: "Safari",
        versionSearch: "Version"
    }, {
        prop: window.opera,
        identity: "Opera"
    }, {
        string: navigator.vendor,
        subString: "iCab",
        identity: "iCab"
    }, {
        string: navigator.vendor,
        subString: "KDE",
        identity: "Konqueror"
    }, {
        string: navigator.userAgent,
        subString: "Firefox",
        identity: "Firefox"
    }, {
        string: navigator.vendor,
        subString: "Camino",
        identity: "Camino"
    }, {
        string: navigator.userAgent,
        subString: "Netscape",
        identity: "Netscape"
    }, {
        string: navigator.userAgent,
        subString: "MSIE",
        identity: "Explorer",
        versionSearch: "MSIE"
    }, {
        string: navigator.userAgent,
        subString: "Gecko",
        identity: "Mozilla",
        versionSearch: "rv"
    }, {
        string: navigator.userAgent,
        subString: "Mozilla",
        identity: "Netscape",
        versionSearch: "Mozilla"
    }],
    dataOS: [{
        string: navigator.platform,
        subString: "Win",
        identity: "Windows"
    }, {
        string: navigator.platform,
        subString: "Mac",
        identity: "Mac"
    }, {
        string: navigator.userAgent,
        subString: "iPhone",
        identity: "iPhone/iPod"
    }, {
        string: navigator.platform,
        subString: "Linux",
        identity: "Linux"
    }]
};
BrowserDetect.init();

function isIE6or7or8() {
    if (BrowserDetect.browser == "Explorer" && (BrowserDetect.version == 8 || BrowserDetect.version == 7 || BrowserDetect.version == 6)) {
        return true
    } else {
        return false
    }
}(function(a) {
    a.fn.lister = function(d) {
        a.sortA = [];
        a.sortB = [];
        a.ie = isIE6or7or8();
        var b = this;
        var c = "#" + d;
        var f = a(b).parent("div");
        a.create = function(i) {
            return a(document.createElement(i))
        };

        a.fn.fillLister = function() {
            a(b).scrollTop(0).children("li").each(function() {
                if (!a(this).children("span").hasClass("left")) {
                    var i = a.create("span").addClass("left").html("&#9658;");
                    a(this).bind("click", e).prepend(i)
                }
            })
        };
        a.fn.sendAll = function(j) {
            if (j) {
                var l = b;
                var i = c;
                var k = "&#9668;"
            } else {
                var l = c;
                var i = b;
                var k = "&#9658;"
            }
            a(l).children("li[el]").each(function() {
                var m = a(this);
                var n = a(m).children("span");
                var o = a(this).parent("ul").attr("id");
                a(n).each(function() {
                    a(this).html(k)
                });
                if (j) {
                    a(c).prepend(m)
                } else {
                    a(b).prepend(m)
                }
            });
            a(l).empty()
        };
        a.fn.getList = function(i) {
            var j = [];
            a(c).children("li[el]").each(function() {
                if (!i) {
                    var k = a.trim(a(this).attr("el"))
                } else {
                    var k = a(this).attr(i)
                }
                if (k != "" && k != null) {
                    j.push(a.trim(k))
                }
            });
            return j.toString().replace(/\s+/g, "")
        };
        a.fn.tclass = function(i) {
            a(this).bind("mouseover", function() {
                a(this).addClass(i)
            });
            a(this).bind("mouseout", function() {
                a(this).removeClass(i)
            });
            return this
        };

        function e(j) {
            var i = a(this);
            var k = a(i).children("span");
            var l = a(this).parent("ul").attr("id");
            if (l == d) {
                a(b).prepend(i);
                a(k).removeClass("right").addClass("left").html("&#9658;")
            } else {
                a(c).prepend(i);
                a(k).removeClass("left").addClass("right").html("&#9668;");
                /* 判断是否具有相同值 */

            }
            a(this).removeClass("hover").removeClass("listerHover")
        }
        a.fn.sortLists = function(k) {
            var m = a(k);
            if (!a.ie) {
                a(m).children("li[tx]").each(function() {
                    a(this).remove()
                });
                a(m).parent("div").children("a").each(function() {
                    a(this).remove()
                });
                a(m).parent("div").children("span").each(function() {
                    a(this).remove()
                })
            }
            var i = m.children("li").get();
            i.sort(function(o, n) {
                var q = a(o).text().toUpperCase();
                var p = a(n).text().toUpperCase();
                return (q < p) ? -1 : (q > p) ? 1 : 0
            });
            if (!a.ie) {
                var l = "";
                var j = ""
            }
            a.each(i, function(n, o) {
                if (!a.ie) {
                    j = a(o).text().substring(1, 2);
                    if (j.toUpperCase() != l.toUpperCase()) {
                        a(m).append(a.create("li").text(j).attr("tx", j.toUpperCase()).addClass("liTitles"));
                        a(m).before(a.create("a").bind("click", g).text(j.toUpperCase())).before(a.create("span").text(" | "))
                    }
                }
                m.append(o);
                if (!a.ie) {
                    l = j
                }
            })
        };

        function h() {
            var i = a.create("div").addClass("listerLinksLeft").append(b);
            var j = a.create("div").addClass("listerLinksRight").append(a(c));
            a(f).prepend(i).append(j)
        }
        function g() {
            var j = a(this).parent("div").children("ul");
            var i = a(this).text();
            a(j).scrollTop(0);
            var k = a(j).children("li[tx=" + i + "]").offset().top - a(j).offset().top;
            a(j).animate({
                scrollTop: k
            }, 500)
        }
        if (a(b).parent("div").hasClass("listerLinksLeft") == false) {
            a(b).parent("div").addClass("lister");
            a(b).addClass("lister").children("li").each(function() {
                var i = a.create("span").addClass("left").html("&#9658;");
                a(this).bind("click", e).tclass("listerHover").prepend(i).bind("mouseover", function() {
                    a(this).addClass("hover")
                }).bind("mouseout", function() {
                    a(this).removeClass("hover")
                })
            }).scrollTop(0);
            h();
            a(c).addClass("lister").children("li").each(function() {
                var i = a.create("span").addClass("right").html("&#9668;");
                a(this).bind("click", e).tclass("listerHover").prepend(i)
            }).scrollTop(0)
        }
        return this
    }
})(jQuery);