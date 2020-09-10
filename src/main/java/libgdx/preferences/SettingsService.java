package libgdx.preferences;


public class SettingsService {

    protected PreferencesService preferencesService = new PreferencesService("SettingsService");

    public boolean isSoundOn() {
        return preferencesService.getPreferences().getBoolean(getToggleSoundKey(), true);
    }

    public void toggleSound() {
        preferencesService.putBoolean(getToggleSoundKey(), !isSoundOn());
    }

    private String getToggleSoundKey() {
        return "ToggleSoundKey";
    }
}
