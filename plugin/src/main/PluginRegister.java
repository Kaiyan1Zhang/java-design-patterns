package src.main;

import java.util.*;
import java.util.Arrays;

public interface PluginRegister<T extends Plugin<S>,S> extends Iterable<T>{
    public static <S,T extends Plugin<S>> PluginRegister<T,S> empty(){
        return of(Collections.emptyList());
    }



    @SafeVarargs
    public static<S,T extends Plugin<S>> PluginRegister<T , S> of(T... plugins){
        return of(Arrays.asList(plugins));
    }

    public static<S,T extends Plugin<S>> PluginRegister<T,S> of(List<? extends T> plugins){
        return of(plugins);
    }



    Optional<T> getPluginFor(S delimiter);

    List<T> getPluginsFor(S delimiter);

    boolean contains(T plugin);

    boolean hasPluginFor(S delimiter);

    List<T> getPlugins();

    int countPlugins();






}
