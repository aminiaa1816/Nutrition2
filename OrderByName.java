import java.util.Comparator;


class OrderByName implements Comparator<Recipe>
{
    @Override
    public int compare(Recipe s1, Recipe s2)
    {
        return s1.getName().compareTo(s2.getName());
    }
}