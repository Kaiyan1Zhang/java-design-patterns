package src.main;



import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class SimplePluginRegister<T extends Plugin<S>,S> implements PluginRegister<T,S>,Iterable<T> {
    private List<T> pluginList;
    private boolean seek;

    protected SimplePluginRegister(List<? extends T> pluginList){
        Assert.notNull(pluginList, "pluginList can not be null");
        this.pluginList = pluginList == null ? new ArrayList<>() : (List<T>) pluginList;
        this.seek = false;
    }

    protected  synchronized  List<T> initialize(List<T> pluginList){
        Assert.notNull(pluginList,"pluginList can not be null");
        return pluginList.stream().filter(it -> it !=null).collect(Collectors.toList());
    }



    public List<T> getPlugins() {
        if(!seek){
            this.pluginList = initialize(this.pluginList);
            this.seek = true;
        }
        return  pluginList;
    }

    public static <S,T extends Plugin<S>> SimplePluginRegister<T,S> empty(){
        return of(Collections.emptyList());
    }


    @SafeVarargs
    public static<S,T extends Plugin<S>> SimplePluginRegister<T , S> of(T... plugins){
        return of(Arrays.asList(plugins));
    }

    public static<S,T extends Plugin<S>> SimplePluginRegister<T,S> of(List<? extends T> plugins){
        return of(plugins);
    }


    @Override
    public Optional<T> getPluginFor(S delimiter) {
        Assert.notNull(delimiter,"delimiter con not be null");
        return getPlugins().stream().filter(it -> it.supports(delimiter)).findFirst();
    }

    @Override
    public List<T> getPluginsFor(S delimiter) {
        Assert.notNull(delimiter,"delimiter con not be null");
        return getPlugins().stream().filter(it -> it.supports(delimiter)).collect(Collectors.toList());
    }

    @Override
    public boolean contains(T plugin) {
        return getPlugins().contains(plugin);
    }

    @Override
    public boolean hasPluginFor(S delimiter) {
        return getPluginFor(delimiter).isPresent();
    }



    @Override
    public int countPlugins() {
        return getPlugins().size();
    }

    @Override
    public Iterator<T> iterator() {
        return getPlugins().iterator();
    }
}
