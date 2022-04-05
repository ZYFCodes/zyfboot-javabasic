package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description 数据清洗：去除相关特殊符号
 * @date 2022/4/5  14:25
 */
@Component
public class RemoveSpecialSymbols implements ContextHandler<ContentInfoContext, SensitveHitContext> {

    private LoadingCache<String, Set<Integer>> specialSymbolsCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            /*构建缓存*/
            .build(new CacheLoader<String, Set<Integer>>() {
                /*初始化加载数据的缓存信息*/
                @Override
                public Set<Integer> load(String specialSymbols) throws Exception {
                    return getSpecialSymbols();
                }
            });

    /**
     * 对用户内容进行处理：将用户输入中含有的特殊符号进行排除
     *
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词词库校验）
     */
    @Override
    public void handle(ContentInfoContext context, SensitveHitContext dealRes) {
        /*前置节点处理异常，本节点不做处理*/
        if (!dealRes.getDeliver()) {
            return;
        }

        try {
            String SPECIAL_SYMBOLS = "specialSymbols";
            char[] valueChars = dealRes.getCleanContent().toCharArray();
            Set<Integer> specialSymbols = specialSymbolsCache.get(SPECIAL_SYMBOLS);
            StringBuilder cleanContent = new StringBuilder();
            for (char valueChar : valueChars) {
                int temp = BCConvert.charConvert(valueChar);
                if (specialSymbols.contains(temp)) {
                    /*过滤特殊字符*/
                    continue;
                }
                cleanContent.append(valueChar);
            }

            /*将清洗数据载入待校验实体中*/
            dealRes.setDeliver(true);
            dealRes.setCleanContent(cleanContent.toString());
        } catch (Exception e) {
            dealRes.setDeliver(false);
            dealRes.setReason("数据清洗异常：排除特殊符号失败");
        }
    }

    /**
     * 本处举例，实际应该放置到指定的配置页面来实时生效
     *
     * @return 相关特殊符号集合
     */
    private static Set<Integer> getSpecialSymbols() {
        List<String> specialSymbolsRes = Lists.newArrayList();
        String speciSymbols = "'͏@¥^…&（）()、。 ；：|【】[]{}-—_%*$#！/\\<>《》，,.:“”\"』『•‘’'?？+=！!" +
                "°❤❥웃유☮☏☢☠✔☑♚▲♪✈✞÷↑↓◆◇⊙■□△▽¿─│♥❣♂♀☿✉☣☤✘☒♛▼♫⌘☪≈←→◈◎☉★☆⊿※¡━\u2069┃♡ღツ☼☁❅✎©®™Σ✪✯☭➳卐√↖↗●◐Θ◤◥︻" +
                "〖〗┄┆℃℉°✿ϟ☃☂✄¢€£∞✫★½✡×↙↘○◑⊕◣◢︼【】┅┇☽☾✚〓▂▃▄▅▆▇█▉▊▋▌▍▎▏↔↕☽☾の•▸◂▴▾┈┊①②③④⑤⑥⑦⑧⑨⑩" +
                "ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩ㍿▓♨♛❖☪✙┉┋☹☺☻تヅツッシÜϡﭢ™℠℗©®♥❤❥❣❦❧♡۵웃유ღ♂♀☿☼☀☁☂☄☾☽❄☃☈⊙☉℃℉❅✺ϟ☇♤♧♡♢♠♣♥♦☜☞☚☛☟✽✾✿❁❃❋❀⚘☑✓✔" +
                "√☐☒✗✘ㄨ✕✖✖⋆✢✣✤✥❋✦✧✩✰✪✫✬✭✮✯❂✡★✱✲✳✴✵✶✷✸✹✺✻✼❄❅❆❇❈❉❊†☨✞✝☥☦☓☩☯☧☬☸✡♁✙♆。，、＇：∶；?‘’“”〝〞ˆˇ﹕︰﹔﹖﹑•¨….¸;！" +
                "´？！～—ˉ｜‖＂〃｀@﹫¡¿﹏﹋﹌︴々﹟#﹩$﹠&﹪%*﹡﹢﹦﹤‐￣¯―﹨ˆ˜﹍﹎+=<＿_-\\ˇ~﹉﹊（）〈〉‹›﹛﹜『』〖〗［］《》〔〕" +
                "{}「」【】︵︷︿︹︽_﹁﹃︻︶︸﹀︺︾ˉ﹂﹄︼☩☨☦✞✛✜✝✙✠✚†‡◉○◌◍◎●◐◑◒◓◔◕◖◗❂☢⊗⊙◘◙◍⅟½⅓⅕⅙⅛⅔⅖⅚⅜¾⅗⅝⅞⅘≂≃≄≅≆≇≈≉≊≋≌≍≎≏≐≑≒≓" +
                "≔≕≖≗≘≙≚≛≜≝≞≟≠≡≢≣≤≥≦≧≨≩⊰⊱⋛⋚∫∬∭∮∯∰∱∲∳%℅‰‱㉿囍♔♕♖♗♘♙♚♛♜♝♞♟ℂℍℕℙℚℝℤℬℰℯℱℊℋℎℐℒℓℳℴ℘ℛℭ℮ℌℑℜℨ♪♫♩♬♭♮♯°ø☮☪✡☭✯卐✐" +
                "✎✏✑✒✉✁✂✃✄✆✉☎☏➟➡➢➣➤➥➦➧➨➚➘➙➛➜➝➞➸➲➳⏎➴➵➶➷➸➹➺➻➼➽←↑→↓↔↕↖↗↘↙↚↛↜↝↞↟↠↡↢↣↤↥↦↧↨➫➬➩➪➭➮➯➱↩↪↫↬↭↮↯↰↱↲↳↴↵↶↷↸↹↺↻↼↽↾↿⇀⇁⇂⇃⇄⇅⇆" +
                "⇇⇈⇉⇊⇋⇌⇍⇎⇏⇐⇑⇒⇓⇔⇕⇖⇗⇘⇙⇚⇛⇜⇝⇞⇟⇠⇡⇢⇣⇤⇥⇦⇧⇨⇩⇪➀➁➂➃➄➅➆➇➈➉➊➋➌➍➎➏➐➑➒➓ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅬⅭⅮⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹⅺⅻⅼⅽⅾ┌┍┎┏┐┑┒┓" +
                "└┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄╅╆╇╈╉╊╋╌╍╎╏═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬◤◥◄►▶◀◣◢▲▼◥▸◂▴▾" +
                "△▽▷◁⊿▻◅▵▿▹◃❏❐❑❒▀▁▂▃▄▅▆▇▉▊▋█▌▍▎▏▐░▒▓▔▕■□▢▣▤▥▦▧▨▩▪▫▬▭▮▯" +
                "㋀㋁㋂㋃㋄㋅㋆㋇㋈㋉㋊㋋㏠㏡㏢㏣㏤㏥㏦㏧㏨㏩㏪㏫㏬㏭㏮㏯㏰㏱㏲㏳㏴㏵㏶㏷㏸㏹㏺㏻㏼㏽㏾㍙㍚㍛㍜㍝㍞㍟㍠㍡㍢㍣㍤㍥㍦㍧㍨㍩㍪㍫㍬㍭㍮㍯㍰㍘" +
                "☰☲☱☴☵☶☳☷☯♂♀✲☀☼☾☽◐◑☺☻☎☏✿❀№↑↓←→√×÷★℃℉°◆◇⊙■□△▽¿½☯✡㍿卍卐♂♀✚〓㎡♪♫♩♬囍Φ♀♂‖$@*&#※卍卐Ψ♫♬♭♩♪♯♮⌒¶∮‖€￡¥$" +
                "①②③④⑤⑥⑦⑧⑨⑩⑪⑫⑬⑭⑮⑯⑰⑱⑲⑳⓪⓿❶❷❸❹❺❻❼❽❾❿⓫⓬⓭⓮⓯⓰⓱⓲⓳⓴⓵⓶⓷⓸⓹⓺⓻⓼⓽⓾⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇" +
                "⒈⒉⒊⒋⒌⒍⒎⒏⒐⒑⒒⒓⒔⒕⒖⒗⒘⒙⒚⒛ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ" +
                "﹢﹣×÷±+-*/^=≌∽≦≧≒﹤﹥≈≡≠≤≥≮≯∷∶∝∞∧∨∑∏∪∩∈∵∴⊥∥∠⌒⊙√∛∜∟⊿㏒㏑%‰⅟½⅓⅕⅙⅐⅛⅑⅒⅔¾⅖⅗⅘⅚⅜⅝⅞≂≃≄≅≆≇≉≊≋≍≎≏≐≑≓≔≕≖≗≘≙≚≛≜≝≞≟≢≣≨≩⊰⊱⋛⋚" +
                "∫∮∬∭∯∰∱∲∳℅øπ∀∁∂∃∄∅∆∇∉∊∋∌∍∎∐−∓∔∕∖∗∘∙∡∢∣∤∦∸∹∺∻∼∾∿≀≁≪≫≬≭≰≱≲≳≴≵≶≷≸≹≺≻≼≽≾≿⊀⊁⊂⊃⊄⊅⊆⊇⊈⊉⊊⊋⊌⊍⊎⊏⊐⊑⊒⊓⊔⊕⊖⊗⊘⊚⊛⊜⊝⊞⊟⊠⊡" +
                "⊢⊣⊤⊦⊧⊨⊩⊪⊫⊬⊭⊮⊯⊲⊳⊴⊵⊶⊷⊸⊹⊺⊻⊼⊽⊾⋀⋁⋂⋃⋄⋅⋆⋇⋈⋉⋊⋋⋌⋍⋎⋏⋐⋑⋒⋓⋔⋕⋖⋗⋘⋙⋜⋝⋞⋟⋠⋡⋢⋣⋤⋥⋦⋧⋨⋩⋪⋫⋬⋭⋮⋯⋰⋱⋲⋳⋴⋵⋶⋷⋸⋹⋺⋻⋼⋽⋾⋿" +
                "ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅬⅭⅮↁↂↃↅↆↇↈ↉↊↋■□▢▣▤▥▦▧▨▩▪▫▬▭▮▯▰▱▲△▴▵▶▷▸▹►▻▼▽▾▿◀◁◂◃◄◅◆◇◈◉◊○◌◍◎●◐◑◒◓◔◕◖◗◘◙◚◛◜◝◞◟◠◡◢◣◤◥" +
                "◦◧◨◩◪◫◬◭◮◯◰◱◲◳◴◵◶◷◸◹◺◿◻◼◽◾⏢⏥⌓⌔⌖⁰¹²³⁴⁵⁶⁷⁸⁹⁺⁻⁼⁽⁾ⁿ₀₁₂₃₄₅₆₇₈₉₊₋₌₍₎ₐₑₒₓₔₕₖₗₘₙₚₛₜ。，、＇：∶；?‘’“”〝〞ˆˇ﹕︰﹔﹖﹑•¨….¸;" +
                "！´？！～—ˉ｜‖＂〃｀@﹫¡¿﹏﹋﹌︴々﹟#﹩$﹠&﹪%*﹡﹢﹦﹤‐￣¯―﹨ˆ˜﹍﹎+=<＿_-\\ˇ~﹉﹊（）〈〉‹›﹛﹜『』〖〗［］《》〔〕{}「」【】" +
                "︵︷︿︹︽_﹁﹃︻︶︸﹀︺︾ˉ﹂﹄︼❝❞‐‑‒–―‖‗‘’‚‛“”„‟†‡•‣․‥…‧\u202A\u202B\u202C\u202D\u202E‰‱′″‴‵‶‷‸※‼‽‾‿⁀⁁⁂⁃⁄⁇⁈⁉⁊⁋⁌⁍⁎⁏" +
                "⁐⁑⁒⁓⁔⁕⁖⁗⁘⁙⁚⁛⁜⁝⁞\u2060\u2061\u2062\u2063\u2064°′″＄￥〒￠￡％＠℃℉﹩﹪‰﹫㎡㎥³㎜㎟㎣㎝㎠㎤㍷㍸㍹㎞㎢㎦㏎㎚㎛㏕㎍㎎㎏㏄º○¤%$º¹²³" +
                "㍺㎀㎁㎂㎃㎄㎅㎆㎇㎈㎉㎊㎋㎌㎐㎑㎒㎓㎔㎕㎖㎗㎘㎙㎧㎨㎩㎪㎫㎬㎭㎮㎯㎰㎱㎲㎳㎴㎵㎶㎷㎸㎹㎺㎻㎼㎽㎾㎿㏀㏁㏂㏃㏄㏅㏆㏇㏈㏉㏊㏋㏌㏍㏎㏏㏐㏑㏒㏓㏔㏕㏖㏗㏘㏙㏚㏛㏜" +
                "㏝㏞㏟㍱㍲㍳㍴㍵㍶€£Ұ₴$₰¢₤¥₳₲₪₵元₣₱฿¤₡₮₭₩ރ円₢₥₫₦ł﷼₠₧₯₨čर₹ƒ₸￠↑↓←→↖↗↘↙↔↕" +
                "➻➼➽➸➳➺➻➴➵➶➷➹▶►▷◁◀◄«»➩➪➫➬➭➮➯➱⏎➲➾➔➘➙➚➛➜➝➞➟➠➡➢➣➤➥➦➧➨↚↛↜↝↞↟↠↠↡↢↣↤↤↥↦↧↨⇄⇅⇆⇇⇈⇉⇊⇋⇌⇍⇎⇏⇐⇑⇒⇓⇔⇖⇗⇘⇙⇜↩↪↫↬↭↮↯↰↱↲↳↴↵↶↷↸↹☇☈↼↽↾↿⇀⇁⇂⇃" +
                "⇞⇟⇠⇡⇢⇣⇤⇥⇦⇧⇨⇩⇪↺↻⇚⇛✐✎✏✑✒✉✁✂✃✄✆✉☎☏☑✓✔√☐☒✗✘ㄨ✕✖✖☢☠☣✈★☆✡囍㍿☯☰☲☱☴☵☶☳☷☜☞☚☛☟♤♧♡♢♠♣♥♦☀☁☂❄☃♨웃유❖☽☾☪✿♂♀✪✯☭➳" +
                "卍卐√×■◆●○◐◑✙☺☻❀⚘♔♕♖♗♘♙♚♛♜♝♞♟♧♡♂♀♠♣♥❤☜☞☎☏⊙◎☺☻☼▧▨♨◐◑↔↕▪▒◊◦▣▤▥▦▩◘◈◇♬♪♩♭♪の★☆→あぃ￡Ю〓§♤♥▶¤✲❈✿✲❈➹☀☂☁【】" +
                "┱┲❣✚✪✣✤✥✦❉❥❦❧❃❂❁❀✄☪☣☢☠☭ღ▶▷◀◁☀☁☂☃☄★☆☇☈⊙☊☋☌☍ⓛⓞⓥⓔ╬『』∴☀♫♬♩♭♪☆∷﹌の★◎▶☺☻►◄▧▨♨◐◑↔↕↘▀▄█▌◦☼♪の☆→♧ぃ￡❤▒▬♦◊◦♠♣▣۰•❤•۰" +
                "►◄▧▨♨◐◑↔↕▪▫☼♦⊙●○①⊕◎Θ⊙¤㊣★☆♀◆◇◣◢◥▲▼△▽⊿◤◥✐✡✓✔✕✖♂♀♥♡☜☞☎☏⊙◎☺☻►◄▧▨♨◐◑↔↕♥♡▪▫☼♦▀▄█▌▐░▒▬♦◊◘◙◦☼♠♣▣▤▥▦▩◘◙◈" +
                "♫♬♪♩♭♪✄☪☣☢☠♯♩♪♫♬♭♮☎☏☪ºº₪¤큐«»™♂✿♥◕‿-｡｡◕‿◕｡āáǎàōóǒòēéěèīíǐìūúǔùǖǘǚǜüêɑ\uE7C7ńň\uE7C8ɡ" +
                "ㄅㄆㄇㄈㄉㄊㄋㄌㄍㄎㄏㄐㄑㄒㄓㄔㄕㄖㄗㄘㄙㄚㄛㄜㄝㄞㄟㄠㄡㄢㄣㄤㄥㄦㄧㄨㄩ々〆のぁ〡〢〣〤〥〦〧〨〩─━│┃╌╍╎╏┄┅┆┇┈┉┊┋" +
                "┌┍┎┏┐┑┒┓└┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄╅╆╇╈╉╊╋╪╫╬═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╳╔╗╝╚╬═╓╩┠┨┯┷┏┓┗┛┳" +
                "⊥﹃﹄┌╮╭╯╰♚♛♝♞♜♟♔♕♗♘♖♟͏\u2069\u2060\u2061\u2062\u2063\u2064\u2067\u206C\u206B";
        if (StringUtils.isNotBlank(speciSymbols)) {
            for (int index = 0; index < speciSymbols.length(); index++) {
                specialSymbolsRes.add(String.valueOf(speciSymbols.charAt(index)));
            }
        }

        Set<Integer> specialSymbolsSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(specialSymbolsRes)) {
            char[] chs;
            for (String curr : specialSymbolsRes) {
                chs = curr.toCharArray();
                for (char c : chs) {
                    specialSymbolsSet.add(BCConvert.charConvert(c));
                }
            }
        }
        return specialSymbolsSet;
    }

    public static void main(String[] args) {
        String content = "你₴$₰¢₤¥₳₲₪₵元₣₱฿¤₡₮₭₩ރ円₢好❆❇❈，张┶┷┸┹┺┻┼┽┾彦㎵㎶㎷㎸㎹㎺峰⓳⓴⓵⓶⓷！";
        char[] valueChars = content.toCharArray();
        Set<Integer> specialSymbols = getSpecialSymbols();
        StringBuilder cleanContent = new StringBuilder();
        for (char valueChar : valueChars) {
            int temp = BCConvert.charConvert(valueChar);
            if (specialSymbols.contains(temp)) {
                /*过滤特殊字符*/
                continue;
            }
            cleanContent.append(valueChar);
        }
        System.out.println(cleanContent.toString());
    }


}
