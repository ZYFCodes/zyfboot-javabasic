package org.zyf.javabasic.dynamicbizvalidator.dynamicdeal;

import javax.validation.MessageInterpolator;
import java.util.Locale;

/**
 * @author yanfengzhang
 * @description 本地信息处理拦截器
 * @date 2023/3/9  23:15
 */
public class LocaleMessageInterpolator implements MessageInterpolator {
    private final MessageInterpolator defaultInterpolator;
    private final Locale defaultLocale;

    public LocaleMessageInterpolator(MessageInterpolator interpolator, Locale locale) {
        this.defaultLocale = locale;
        this.defaultInterpolator = interpolator;
    }

    @Override
    public String interpolate(String message, Context context) {
        return defaultInterpolator.interpolate(message,
                context,
                this.defaultLocale);
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
        return defaultInterpolator.interpolate(message, context, this.defaultLocale);
    }
}
