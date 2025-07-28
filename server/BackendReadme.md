<style>
    h3{
        color: red;
        font-weight: bold;
        border-bottom: 1px solid red;
        padding-bottom: 5px;
    }
</style>
### N + 1 Select Problem in Fetching all blogs

#### What actually happens here is
- Main Blog Fetch Query to fetch all blogs
- Additional Queries for Category and User 
    - These are triggered for every single blog entry - meaning if i have 10 blogs and each one has a Category and User, i'll get :- 
    - **10 Queries for categories**
    - **10 Queries for users**
    - This is because of **lazy Loading** in my entity relationships
- Total - `1 + 2N` queries which is inefficient.

#### Solution :-
- Use `@EntityGraph` or `Join Fetch`

#### Option 1:
```java
@EntityGraph(attributePaths = {"user", "category"})
List<Blog> findAll();
```
This tells Hibernate: “When you fetch blogs, also fetch the associated user and category in the same query.” - 1 single Query using JOINS.

#### Option 2:
```java
@Query("SELECT b FROM Blog b JOIN FETCH b.user JOIN FETCH b.category")
List<Blog> findAllWithUserAndCategory();

```

### Avoiding unnecessary database SELECTs when you only need to refer to an entity by its ID (not fetch its full data).
```java
Category category = categoryDao.getReferenceById(blogRequestDTO.getCategory_Id());
```
- The object is a proxy with only the ID field.
- When blog is saved, Hibernate only uses the ID of category for the update query.

**Without `getReferenceById()` (using `findById()`):**
```java
Category category = categoryDao.findById(blogRequestDTO.getCategory_Id()).orElseThrow(...);
blog.setCategory(category);
```
- This triggers: `SELECT * FROM category WHERE id = ?;`
- But I just wanted to assign the Category to the blog — i didn’t actually use the category's fields like title or description. So this query was wasted.

