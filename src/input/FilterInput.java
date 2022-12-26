package input;

public class FilterInput {
    private SortInput sort;
    private ContainsInput contains;


    /**
     * getter for sort
     * @return  sort
     */
    public SortInput getSort() {
        return sort;
    }


    /**
     * setter for sort
     * @param sort  sort
     */
    public void setSort(final SortInput sort) {
        this.sort = sort;
    }


    /**
     * setter for sort
     * @return  sort
     */
    public ContainsInput getContains() {
        return contains;
    }

}
