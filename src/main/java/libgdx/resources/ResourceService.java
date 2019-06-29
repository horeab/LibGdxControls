package libgdx.resources;

import com.badlogic.gdx.Gdx;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import libgdx.game.Game;
import libgdx.game.SubGameDependencyManager;
import libgdx.utils.EnumUtils;


public abstract class ResourceService {

    public Res getByPath(String path) {
        getAllRes();
        for (Res resource : getAllRes()) {
            if (resource.getPath().equals(path)) {
                return resource;
            }
        }
        return null;
    }

    public Res getByName(String name) {
        for (Res resource : getAllRes()) {
            if (resource.name().equals(name)) {
                return resource;
            }
        }
        return null;
    }

    public Set<Res> getAllRes() {
        Set<Res> allRes = new HashSet<Res>(Arrays.asList(MainResource.values()));
        allRes.addAll(Arrays.asList((Res[]) EnumUtils.getValues(Game.getInstance().getMainDependencyManager().getMainResourcesClass())));
        allRes.addAll(new SpecificResourceService().getAllRes());
        return allRes;
    }

    public Res getOverridableRes(Res res) {
        Res result = res;
        try {
            String path = res.getPath();
            String extension = path.substring(path.lastIndexOf("."));
            String resLanguagePath = createPath(res.name() + "_" + Game.getInstance().getAppInfoService().getLanguage(), extension);
            String resPath = createPath(res.name(), extension);
            if (resourceExists(resLanguagePath)) {
                result = new ResImpl(resLanguagePath, res.getClassType(), res.name());
            } else if (resourceExists(resPath)) {
                result = new ResImpl(resPath, res.getClassType(), res.name());
            }
            return result;
        } catch (NullPointerException e) {
            System.out.println("Resource not found: " + res.getPath());
            throw new RuntimeException("Resource not found: " + res.getPath());
        }

    }

    private boolean resourceExists(String path) {
        return Gdx.files.internal(path).exists();
    }

    private String createPath(String resourceName, String extension) {
        return Game.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + resourceName + extension;
    }

}
