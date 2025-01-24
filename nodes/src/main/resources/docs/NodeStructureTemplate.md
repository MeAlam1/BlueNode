# Node JSON Structure Template

---

## Node Structure

A node is an object with the following fields:

---

### `title`
- **Type:** String
- **Description:** The display name of the node. This is shown to the user in the editor.
- **Example:** `"Node Title"`

---

### `description`
- **Type:** String
- **Description:** A brief explanation of the node’s functionality or purpose.
- **Example:** `"This is a description of the node."`

---

### `color`
- **Type:** String (Hex Color Code)
- **Description:** The color used to visually represent the node in the editor.
- **Example:** `"#FF5733"`

---

### `category`
- **Type:** String
- **Description:** The category under which the node is classified. This helps organize nodes within the editor.
- **Options:** `"Math"`, etc.
- **Example:** `"Math"`

---

### `inputs`
- **Type:** Array of Objects
- **Description:** A list of input fields for the node. Each input has the following structure:
    - **`id`**: A unique identifier for the input.
        - **Type:** String
        - **Example:** `"input1"`

    - **`name`**: The name of the input, displayed in the editor.
        - **Type:** String
        - **Example:** `"Input Name"`

    - **`type`**: The data type of the input.
        - **Type:** String
        - **Options:** `"short_string"`, `"long_string"`, `"number"`, `"boolean"`, etc.
        - **Aliases:** Some types have aliases. For example:
            - `"long_string"` can also be referenced as `"textarea"`.
            - `"boolean"` may be referenced as `"checkbox"`.  
              These aliases serve as alternate names for the same underlying type.

    - **`defaultValue`**: The default value for the input.
        - **Type:** Depends on `type`
        - **Example:** `0`

  **Example Input Structure:**
  ```json
  {
    "id": "input1",
    "name": "Input Name",
    "type": "number",
    "defaultValue": 0
  }
  ```

---

### `outputs`
- **Type:** Array of Objects
- **Description:** A list of outputs for the node. Each output has the following structure:
    - **`id`**: A unique identifier for the output.
        - **Type:** String
        - **Example:** `"output1"`

    - **`name`**: The name of the output, displayed in the editor.
        - **Type:** String
        - **Example:** `"Output Name"`

    - **`type`**: The data type of the output.
        - **Type:** String
        - **Options:** `"short_string"`, `"long_string"`, `"number"`, `"boolean"`, etc.
        - **Aliases:** See the **Inputs** section for type aliases.

  **Example Output Structure:**
  ```json
  {
    "id": "output1",
    "name": "Output Name",
    "type": "number"
  }
  ```

---

### `metadata`
- **Type:** Object
- **Description:** Additional information about the node, such as timestamps and tags. It contains the following fields:
    - **`createdBy`**: The user who created the node.
        - **Type:** String
        - **Example:** `null` (Generated through Code)

    - **`createdAt`**: The timestamp when the node was created.
        - **Type:** String (ISO 8601 format)
        - **Example:** `null` (Generated through Code)

    - **`updatedAt`**: The timestamp when the node was last updated.
        - **Type:** String (ISO 8601 format)
        - **Example:** `null` (Generated through Code)

    - **`tags`**: An array of tags associated with the node.
        - **Type:** Array of Strings
        - **Example:** `["example", "node", "editor"]`

  **Example Metadata Structure:**
  ```json
  {
    "createdBy": null,
    "createdAt": null,
    "updatedAt": null,
    "tags": ["example", "node", "editor"]
  }
  ```

---

### Complete Node Example
Here’s a complete example of a node following this structure:
```json
{
  "id": null,
  "title": "Node Title",
  "description": "This is a description of the node.",
  "color": "#FF5733",
  "category": "Math",
  "inputs": [
    {
      "id": "input1",
      "name": "Input Name",
      "type": "number",
      "defaultValue": 0
    }
  ],
  "outputs": [
    {
      "id": "output1",
      "name": "Output Name",
      "type": "number"
    }
  ],
  "metadata": {
    "createdBy": null,
    "createdAt": null,
    "updatedAt": null,
    "tags": ["example", "node", "editor"]
  }
}
```  

---

### Notes on Aliases for Data Types
- Aliases are provided to improve flexibility. They allow developers to use different terminologies for the same type based on context or preference.
    - List:
    - `"long_string"`: `"ls"`, `"textarea"`
    - `"boolean"`: `"bool"`, `"checkbox"`
    - `"number"`: `"num"`, `"int"`
    - `"short_string"`: `"ss"`, `"text"`
    - `"array"`: `"arr"`, `"list"`
    - `"object"`: `"obj"`, `"dict"`