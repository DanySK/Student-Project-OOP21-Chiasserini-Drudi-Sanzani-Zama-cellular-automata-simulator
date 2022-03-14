package casim.controller.menu;

import java.util.List;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import casim.utils.PlayableAutomaton;

/**
 * Implementation of {@link MenuController}.
 *
 */
public class MenuControllerImpl implements MenuController {

    @Override
    public List<MenuItem> getMenuItems() {
        final var reflections = new Reflections("casim.model");
        return reflections.getTypesAnnotatedWith(PlayableAutomaton.class).stream()
            .map(cl -> new MenuItem(cl.getAnnotation(PlayableAutomaton.class).AutomatonName(), cl, null))
            .collect(Collectors.toList());
    }

}
