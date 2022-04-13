package casim.ui.components.page;

import java.util.ArrayDeque;
import java.util.Deque;

import casim.utils.Empty;
import casim.utils.Result;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * A container that can hold multiple ui "pages" and shows the most recent one.
 */
public class PageContainer extends AnchorPane {

    private static final String NO_PREVIOUS_PAGE = "There is no previous page"; 

    private final Deque<Node> pages;
    private final Window owner;

    /**
     * Construct a {@link PageContainer}.
     * 
     * @param owner the {@link Window} which own the {@link PageContainer}.
     */
    public PageContainer(final Window owner) {
        this.pages = new ArrayDeque<>();
        this.owner = owner;
    }

    /**
     * Get the owner.
     * 
     * @return the owner of the container.
     */
    public Window getOwner() {
        return this.owner;
    }

    /**
     * Add a page to the container and show display it.
     * 
     * @param page the page to add.
     */
    public void addPage(final Node page) {
        this.pages.push(page);
        this.showCurrentPage();
    }

    /**
     * Remove the top page, and display the previous one if present.
     * Returns:
     *  - {@link Result} of {@link Empty} is there is no error.
     *  - {@link Result} with IllegalStateException if there is no previous page.
     * 
     * @return {@link Result} with the outcome of the operation.
     */
    public Result<Empty> popPage() {
        final var output = Result.ofEmpty()
            .require(x -> !this.pages.isEmpty(), new IllegalStateException(NO_PREVIOUS_PAGE));

        output.ifPresent(x -> {
            this.pages.pop();
            this.showCurrentPage();
        });

        return output;
    }

    private void showCurrentPage() {
        this.getChildren().clear();
        if (!this.pages.isEmpty()) {
            this.getChildren().add(this.pages.peek());
        }
    }
}
