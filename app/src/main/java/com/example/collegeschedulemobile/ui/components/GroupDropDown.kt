package com.example.collegeschedulemobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.collegeschedulemobile.ui.theme.darkBackground
import com.example.collegeschedulemobile.ui.theme.mainRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDropDown(groups: List<String>, selectedGroup: String, onGroupSelected: (String) -> Unit)
{
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
    {
        OutlinedTextField(
            value = selectedGroup,
            onValueChange = {},
            readOnly = true,
            label = { Text("Выберите группу", color = if (expanded) mainRed else mainRed) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = mainRed,
                unfocusedBorderColor = mainRed,
                focusedLabelColor = mainRed,
                cursorColor = mainRed,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(darkBackground).heightIn(max = 300.dp)
        ) {
            groups.forEach { group ->
                DropdownMenuItem(
                    text = { Text(text = group, color = if (group == selectedGroup) mainRed else Color.White) },
                    onClick = {
                        onGroupSelected(group)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}