package br.com.android.curso.equipe.instagram;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pitang.mobile.infraestrutura.abstracts.AbstractDataBaseAplicacao;
import com.pitang.mobile.infraestrutura.abstracts.AbstractSQLHelper;
import com.pitang.mobile.infraestrutura.util.SharePreferenceUtils;

import br.com.android.curso.equipe.instagram.model.helper.AplicacaoHelper;

public class Aplicacao extends AbstractDataBaseAplicacao {
    public static SharePreferenceUtils preferenceUtils;
    @Override
    public void onCreate() {
        super.onCreate();

        preferenceUtils = new SharePreferenceUtils(this);

        AplicacaoHelper.inicializar(this);
        configImageLoader();
    }

    private void configImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
        .memoryCacheSize(2 * 1024 * 1024)
        .diskCacheSize(50 * 1024 * 1024)
        .diskCacheFileCount(100)
        .denyCacheImageMultipleSizesInMemory()
        .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public AbstractSQLHelper getSQlHelper() {
        return AplicacaoHelper.getInstancia(this);
    }
}
