package com.pitang.mobile.infraestrutura.abstracts;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.pitang.mobile.infraestrutura.R;

import javax.inject.Inject;

public class ActivityRecursos {

    protected FragmentActivity activity;
    protected Resources resources;
    protected ViewGroup container;

    private LayoutInflater layoutInflater;
    private long shortAnimationDuration;

    public ActivityRecursos(FragmentActivity activity) {
        this.activity = activity;
        this.layoutInflater = activity.getLayoutInflater();
        this.resources = activity.getResources();

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime);
    }

    public ActivityRecursos(FragmentActivity activity, ViewGroup container) {
        this.activity = activity;
        this.container = container;
        this.layoutInflater = activity.getLayoutInflater();
        this.resources = activity.getResources();
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        activity.startActivityForResult(intent,requestCode);
    }

    public void startActivityFromFragment(Fragment fragment,Intent intent, int requestCode) {
        activity.startActivityFromFragment(fragment,intent, requestCode);
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public Resources getResources() {
        return resources;
    }

    protected FragmentManager getFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    protected ActionBar getActionBar() {
        return activity.getActionBar();
    }

    protected String[] getStringArray(int stringArrayId) {
        return resources.getStringArray(stringArrayId);
    }

    protected String getString(int stringId) {
        return resources.getString(stringId);
    }

    protected String getString(int id, Object... formatArgs) {
        return resources.getString(id, formatArgs);
    }

    protected int getColor(int colorId) {
        return resources.getColor(colorId);
    }

    protected Drawable getDrawable(int drawableId) {
        return resources.getDrawable(drawableId);
    }

    protected float getDimension(int dimensionId) {
        return resources.getDimension(dimensionId);
    }

    protected int getIdentifier(String name, String defType, String defPackage) {
        return resources.getIdentifier(name, defType, defPackage);
    }

    public View inflateView(int layoutId) {
        return layoutInflater.inflate(layoutId, container, false);
    }

    public View inflateView(int layoutId, ViewGroup container) {
        return layoutInflater.inflate(layoutId, container, false);
    }

    protected Intent getIntent() {
        return activity.getIntent();
    }

    public void esconderTeclado() {
        View view = activity.getCurrentFocus();
        esconderTeclado(view);
    }

    public void esconderTeclado(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void exibirViewFadeIn(View view) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1f).setDuration(shortAnimationDuration);
    }

    protected void setCampoObrigatorioError(TextView textView, boolean error) {
        if (error) {
            textView.setError(getString(R.string.campo_obrigatorio));
        } else {
            textView.setError(null);
        }
    }

    public void esconderViewFadeOut(View view) {
        view.setAlpha(1f);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(0f).setDuration(shortAnimationDuration);
    }
}