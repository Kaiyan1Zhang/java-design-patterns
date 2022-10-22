package src.main;

public interface Plugin<S>{
    boolean supports(S delimiter);
}
