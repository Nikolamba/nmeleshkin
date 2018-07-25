package ru.job4j.http;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
class DispatchPattern {

    private final Map<String, Function<HttpServletRequest, Boolean>> dispatch = new HashMap<>();
    private final ValidateService logic;

    DispatchPattern(ValidateService logic) {
        this.logic = logic;
        this.dispatch.put("add", this.toAdd());
        this.dispatch.put("delete", this.toDelete());
        this.dispatch.put("update", this.toUpdate());
    }

    private Function<HttpServletRequest, Boolean> toAdd() {
        return httpServletRequest -> logic.add(httpServletRequest);

    }

    private Function<HttpServletRequest, Boolean> toDelete() {
        return httpServletRequest -> logic.delete(httpServletRequest);
    }

    private Function<HttpServletRequest, Boolean> toUpdate() {
        return httpServletRequest -> logic.update(httpServletRequest);
    }

    boolean makeAction(String action, HttpServletRequest req) {
        return this.dispatch.get(action).apply(req);
    }
}
