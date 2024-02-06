package prr.core;

public class BasePricingSystem extends PricingSystem{

    private final static String NAME = "Base";


    // TEXT TARIFF
    private final int TEXT_NORMAL_1 = 10;
    private final int TEXT_NORMAL_2 = 16;
        // 2 times the number of characters
    private final int TEXT_NORMAL_3 = 2;

    private final BaseText _normalBaseText;

    private final int TEXT_GOLD_1 = 10;
    private final int TEXT_GOLD_2 = 10;
    private final int TEXT_GOLD_3 = 2;

    private final BaseText _goldBaseText;

    private final int TEXT_PLATINUM_1 = 0;
    private final int TEXT_PLATINUM_2 = 4;
    private final int TEXT_PLATINUM_3 = 4;

    private final BaseText _platinumBaseText;

    //VOICE TARIFF [ values * duration(minutes) ]
    private final int VOICE_NORMAL = 20;
    private final int VOICE_GOLD = 10;
    private final int VOICE_PLATINUM = 10;
    
    //VIDEO TARIFF [values * duration(minutes)]
    private final int VIDEO_NORMAL = 30;
    private final int VIDEO_GOLD = 20;
    private final int VIDEO_PLATINUM = 10;

    public BasePricingSystem(){
        super(NAME);
        _normalBaseText = new BaseText(TEXT_NORMAL_1, TEXT_NORMAL_2, TEXT_NORMAL_3);
        _goldBaseText = new BaseText(TEXT_GOLD_1, TEXT_GOLD_2, TEXT_GOLD_3);
        _platinumBaseText = new PlatinumBaseText(TEXT_PLATINUM_1, TEXT_PLATINUM_2, TEXT_PLATINUM_3);

    }


    @Override
    public int getNormalTextTariff(int length) {
        return _normalBaseText.getTarrif(length);
    }

    @Override
    public int getGoldTextTariff(int length) {
        return _goldBaseText.getTarrif(length);
    }

    @Override
    public int getPlatinumTextTariff(int length) {
        return _platinumBaseText.getTarrif(length);
    }

    @Override
    public int getNormalVoiceTariff(int duration) {
        return VOICE_NORMAL * duration;
    }

    @Override
    public int getGoldVoiceTariff(int duration) {
        return VOICE_GOLD * duration;
    }

    @Override
    public int getPlatinumVoiceTariff(int duration) {
        return VOICE_PLATINUM * duration;
    }

    @Override
    public int getNormalVideoTariff(int duration) {
        return VIDEO_NORMAL * duration;
    }

    @Override
    public int getGoldVideoTariff(int duration) {
    return VIDEO_GOLD * duration;
    }

    @Override
    public int getPlatinumVideoTariff(int duration) {
        return VIDEO_PLATINUM * duration;
    }
    
}
