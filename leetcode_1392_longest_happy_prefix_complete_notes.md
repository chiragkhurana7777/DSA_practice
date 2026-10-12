# LeetCode 1392 — Longest Happy Prefix
## Complete Notes: Brute Force, KMP, and the LPS Array

---

## 1. Problem Statement

Given a string `s`, return its **longest happy prefix**.

A happy prefix is a string that is both:

- a **prefix** of `s` — it starts at index `0`;
- a **suffix** of `s` — it ends at the final index.

The prefix must be **proper**, meaning it cannot be the entire string.

### Example 1

```text
Input:  s = "level"
Output: "l"
```

Prefixes: `"l"`, `"le"`, `"lev"`, `"leve"`

Suffixes: `"l"`, `"el"`, `"vel"`, `"evel"`

The longest string appearing in both lists is `"l"`.

### Example 2

```text
Input:  s = "ababab"
Output: "abab"
```

`"abab"` is a prefix and a suffix:

```text
ababab
^^^^       prefix "abab"
  ^^^^     suffix "abab"
```

The whole string `"ababab"` is not allowed.

### Example 3

```text
Input:  s = "leetcodeleet"
Output: "leet"
```

### Example 4

```text
Input:  s = "abc"
Output: ""
```

No non-empty proper prefix is also a suffix, so the answer is the empty string.

---

## 2. Important Terms

For `s = "abcab"`:

### Prefix

A prefix starts at index `0`.

Proper prefixes:

```text
"a"
"ab"
"abc"
"abca"
```

### Suffix

A suffix ends at the last index.

Proper suffixes:

```text
"b"
"ab"
"cab"
"bcab"
```

The longest common prefix and suffix is `"ab"`.

### Proper prefix

A proper prefix is any prefix shorter than the entire string. The complete string itself is excluded.

### Key observation

If the string length is `n`, a happy prefix can have a length from `0` through `n - 1`. Its length can never be `n`.

---

## 3. Your Two-Pointer / Brute-Force Approach

One way to solve the problem is to try possible prefix and suffix lengths and compare them.

For a candidate length `len`:

```java
String prefix = s.substring(0, len);
String suffix = s.substring(n - len);

if (prefix.equals(suffix)) {
    // This length matches.
}
```

For `s = "ababab"`:

| Candidate length | Prefix | Suffix | Match? |
|---:|---|---|---|
| 1 | `"a"` | `"b"` | No |
| 2 | `"ab"` | `"ab"` | Yes |
| 3 | `"aba"` | `"bab"` | No |
| 4 | `"abab"` | `"abab"` | Yes |
| 5 | `"ababa"` | `"babab"` | No |

The longest match is `"abab"`.

### Why can this approach get TLE?

A string comparison can take up to `O(n)` time. If we compare many candidates, the total work can reach `O(n²)`.

For example, when many characters are equal, each comparison may scan most of the candidate substring before finishing.

The total work can look like:

```text
1 + 2 + 3 + ... + (n - 1)
= n(n - 1) / 2
= O(n²)
```

This is a worst-case bound. Exact runtime can vary with the string and Java's implementation.

### Important correction about the original loop

The original code was:

```java
class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        String ans = "";

        while (l < n - 1 && r > 0) {
            String s1 = s.substring(0, l + 1);
            String s2 = s.substring(r, n);

            if (s1.equals(s2)) {
                ans = s1;
            }

            l++;
            r--;
        }

        return ans;
    }
}
```

This code **does check every proper prefix length from `1` to `n - 1`**. Although `l` and `r` move toward each other, `s.substring(r, n)` always extends from `r` to the end. For example, for `"ababab"`, it checks lengths `1`, `2`, `3`, `4`, and `5`, and returns `"abab"`.

So the logic of checking all these lengths is correct. The concern is the repeated string creation/comparison and its worst-case quadratic time, not that the pointers meet.

---

## 4. The Optimization Idea: Avoid Repeating Work

Suppose we have already discovered that some prefix also matches a suffix. When the next character does not match, we should not necessarily discard all the information we have gathered.

We want to reuse previous comparisons to find a shorter candidate.

This is the central idea behind the **KMP algorithm**.

KMP stands for **Knuth–Morris–Pratt**. It is commonly used for efficient string matching. Its key tool here is the **LPS array**.

---

## 5. What Is LPS?

LPS means:

**Longest Proper Prefix which is also a Suffix**

For each index `i`, `lps[i]` stores the length of the longest proper prefix of the substring `s[0...i]` that is also a suffix of that same substring.

There are three important points:

1. We consider the substring from index `0` through `i`, inclusive.
2. A proper prefix must be shorter than that substring.
3. We store the **length** of the matching prefix, not its final index.

### Example: `s = "abab"`

#### Index 0: `"a"`

There is no non-empty proper prefix/suffix match.

```text
lps[0] = 0
```

#### Index 1: `"ab"`

Proper prefix: `"a"`

Proper suffix: `"b"`

They do not match.

```text
lps[1] = 0
```

#### Index 2: `"aba"`

Proper prefixes: `"a"`, `"ab"`

Proper suffixes: `"a"`, `"ba"`

The longest match is `"a"`, whose length is `1`.

```text
lps[2] = 1
```

#### Index 3: `"abab"`

Proper prefixes: `"a"`, `"ab"`, `"aba"`

Proper suffixes: `"b"`, `"ab"`, `"bab"`

The longest match is `"ab"`, whose length is `2`.

```text
lps[3] = 2
```

Therefore:

```text
String: abab
Index:   0 1 2 3
LPS:     0 0 1 2
```

### Another example: `s = "aabaaab"`

| Index | Substring `s[0...i]` | Longest proper prefix = suffix | LPS |
|---:|---|---|---:|
| 0 | `"a"` | None | 0 |
| 1 | `"aa"` | `"a"` | 1 |
| 2 | `"aab"` | None | 0 |
| 3 | `"aaba"` | `"a"` | 1 |
| 4 | `"aabaa"` | `"aa"` | 2 |
| 5 | `"aabaaa"` | `"a"` | 1 |
| 6 | `"aabaaab"` | `"aab"` | 3 |

So:

```text
LPS = [0, 1, 0, 1, 2, 1, 3]
```

---

## 6. Why Does the Final LPS Value Solve LeetCode 1392?

The problem asks for the longest proper prefix of the entire string that is also its suffix.

That is exactly what `lps[n - 1]` represents.

For:

```text
s = "ababab"
LPS = [0, 0, 1, 2, 3, 4]
```

The final value is:

```java
lps[n - 1] == 4
```

This is the **length** of the longest happy prefix. The answer is:

```java
s.substring(0, lps[n - 1])
```

Java's `substring(start, end)` includes `start` but excludes `end`, so `substring(0, 4)` returns `"abab"`.

If the final LPS value is `0`, the method returns `s.substring(0, 0)`, which is `""`.

---

## 7. How to Build the LPS Array Efficiently

We do not want to compare every possible prefix and suffix from scratch. We build the LPS array from left to right, reusing values already calculated.

We use two variables:

```java
int len = 0;
int i = 1;
```

- `i`: The index currently being processed.
- `len`: The length of the best prefix/suffix match for the already processed portion, which is the current candidate to extend.

Initially, `lps[0] = 0`, so we start at index `1`.

At each step, compare:

```java
s.charAt(i)
s.charAt(len)
```

There are two main cases.

### Case A: The characters match

If:

```java
s.charAt(i) == s.charAt(len)
```

then we can extend the current matching prefix by one character.

```java
len++;
lps[i] = len;
i++;
```

Why?

The previous `len` characters matched at the beginning and end of the current portion. If the next characters match too, the matching length grows by one.

### Case B: The characters do not match

If the characters differ, we cannot extend the current candidate.

There are two subcases.

#### B1. `len > 0`

Do not immediately set `len = 0`.

There may be a shorter prefix that is also a suffix. We use the LPS information already computed:

```java
len = lps[len - 1];
```

Crucially, **do not increment `i` yet**. We need to compare the same character at `i` with the new, shorter candidate.

We may repeat this fallback more than once until we find a match or `len` becomes `0`.

#### B2. `len == 0`

There is no shorter candidate left to try.

```java
lps[i] = 0;
i++;
```

Now we move to the next character.

---

## 8. Why Is the Fallback `lps[len - 1]`?

This is the most important KMP detail.

Suppose `len = 3`. That means the current candidate has length `3`, and it has already matched.

If the next character mismatches, we need to find the **longest shorter prefix that is also a suffix of that matched candidate**.

The LPS value for the candidate of length `3` is stored at index `3 - 1`, which is `2`.

Therefore:

```java
len = lps[len - 1];
```

We use `len - 1` because array indices start at `0`, while `len` represents a length.

### Concrete example

For the string:

```text
s = "aabaaab"
```

At index `5`, the current character is `'a'`. Before processing it, the matching candidate length is `len = 2`.

We compare:

```text
s.charAt(5) = 'a'
s.charAt(2) = 'b'
```

They do not match.

Instead of discarding everything, use:

```java
len = lps[len - 1];
```

So:

```java
len = lps[1]; // 1
```

Now `len = 1`, and we compare the same `s.charAt(5)` again with:

```java
s.charAt(1)
```

This gives the algorithm a chance to find a shorter match without restarting from the beginning.

**Note:** This is the general fallback rule. Depending on the example, the fallback may become `0`, or it may preserve a shorter non-zero candidate.

---

## 9. Full Dry Run: Building LPS for `"abab"`

Initial values:

```text
s = "abab"
lps = [0, 0, 0, 0]
len = 0
i = 1
```

### Step 1: `i = 1`

Compare:

```text
s[1] = 'b'
s[len] = s[0] = 'a'
```

Mismatch, and `len == 0`.

Set:

```text
lps[1] = 0
i = 2
```

Current LPS:

```text
[0, 0, 0, 0]
```

### Step 2: `i = 2`

Compare:

```text
s[2] = 'a'
s[0] = 'a'
```

Match!

```text
len = 1
lps[2] = 1
i = 3
```

Current LPS:

```text
[0, 0, 1, 0]
```

### Step 3: `i = 3`

Compare:

```text
s[3] = 'b'
s[1] = 'b'
```

Match!

```text
len = 2
lps[3] = 2
i = 4
```

Now `i == n`, so the loop stops.

Final LPS:

```text
[0, 0, 1, 2]
```

The final value is `2`, so the longest happy prefix is:

```text
s.substring(0, 2) = "ab"
```

---

## 10. Full Java Solution

```java
class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];

        int len = 0;
        int i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return s.substring(0, lps[n - 1]);
    }
}
```

### Important implementation details

- `lps[0]` is `0` by default.
- The loop begins at `i = 1`.
- On a mismatch with `len > 0`, update `len` but do **not** increment `i`.
- On a mismatch with `len == 0`, set `lps[i] = 0` and increment `i`.
- The string is non-empty for this problem, so `lps[n - 1]` is valid.
- The answer is the prefix of length `lps[n - 1]`.

---

## 11. Complexity Analysis

Let `n` be the length of the string.

### Time: `O(n)`

Although the algorithm contains a `while` loop inside the main loop's logic, the fallback does not make the algorithm quadratic.

- `i` only moves forward, from `1` to `n - 1`.
- `len` increases on a match.
- On a mismatch, `len` decreases to a previously computed LPS value.
- Across the whole computation, these increases and decreases are bounded linearly.

Thus, building the LPS array takes `O(n)` time. Returning the answer with `substring()` takes at most `O(n)` time, so the overall time complexity remains **O(n)**.

### Space: `O(n)`

The LPS array stores one integer per character, requiring `O(n)` extra space.

---

## 12. Brute Force vs. KMP

| Property | Brute force | KMP + LPS |
|---|---|---|
| Main idea | Try candidate lengths and compare prefix/suffix | Reuse prefix/suffix match information |
| Worst-case time | `O(n²)` | `O(n)` |
| Extra space | Depends on implementation and temporary strings | `O(n)` for LPS |
| Ease of understanding | Easier initially | Requires understanding fallback |
| Suitable for large inputs | May TLE | Efficient |

The original two-pointer code is logically valid for checking all proper prefix/suffix lengths. Its worst-case cost can be quadratic because it repeatedly creates and compares substrings. KMP improves the runtime by reusing information.

---

## 13. Common Mistakes

### Mistake 1: Thinking LPS stores an index

Incorrect:

```text
lps[n - 1] = final index of the answer
```

Correct:

```text
lps[n - 1] = length of the answer
```

### Mistake 2: Setting `len = 0` on every mismatch

Incorrect:

```java
else {
    len = 0;
}
```

This throws away useful information and can make the algorithm inefficient or incorrect.

Correct:

```java
if (len > 0) {
    len = lps[len - 1];
} else {
    lps[i] = 0;
    i++;
}
```

### Mistake 3: Incrementing `i` after every mismatch

When `len > 0`, do not increment `i`. Retry the same character with the shorter candidate.

### Mistake 4: Including the whole string

The complete string is not a proper prefix. The LPS definition naturally excludes it.

### Mistake 5: Forgetting that a match means length

If the final LPS value is `4`, the answer has four characters. Return `s.substring(0, 4)`, not `s.substring(0, 5)`.

---

## 14. Quick Revision Sheet

**Problem:** Find the longest proper prefix that is also a suffix.

**Key observation:** The final LPS value gives the answer's length.

**LPS definition:** `lps[i]` is the length of the longest proper prefix of `s[0...i]` that is also a suffix of `s[0...i]`.

**Variables:**

```java
int len = 0; // current candidate match length
int i = 1;   // current index
```

**Match:**

```java
len++;
lps[i] = len;
i++;
```

**Mismatch with a candidate available:**

```java
len = lps[len - 1];
// Do not increment i yet.
```

**Mismatch with no candidate available:**

```java
lps[i] = 0;
i++;
```

**Final answer:**

```java
return s.substring(0, lps[n - 1]);
```

**Complexity:** `O(n)` time and `O(n)` extra space.

---

## 15. Practice Questions

Try these without looking at the solution first.

1. What is the LPS array for `"aaaa"`?
2. What is the LPS array for `"abcabc"`?
3. What is the longest happy prefix of `"level"`?
4. Why do we use `lps[len - 1]` rather than setting `len = 0` on every mismatch?
5. Why must `i` remain unchanged during a fallback?
6. If `lps[n - 1] = 0`, what string should be returned?

### Answers

1. `[0, 1, 2, 3]`
2. `[0, 0, 0, 1, 2, 3]`
3. `"l"`
4. To reuse previous information and try the next-shortest candidate.
5. Because the current character has not yet been processed against the shorter candidate.
6. `""` (the empty string).

---

## Final Mental Model

Think of LPS as a memory of how much of the beginning of the string already matches the end of the portion processed.

When characters match, extend that match.

When characters mismatch, do not throw away all progress. Use `lps[len - 1]` to fall back to a shorter candidate.

Once the entire LPS array is built, its final value tells you exactly how long the longest happy prefix is.
