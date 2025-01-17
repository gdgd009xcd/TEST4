/*
 * Copyright 2024 gdgd009xcd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.zap.extension.automacrobuilder;

import static org.zaproxy.zap.extension.automacrobuilder.zap.ExtensionAutoMacroBuilder.A_TAB_ICON;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

/**
 * @author gdgd009xcd
 */
@SuppressWarnings("serial")
public class MacroBuilderUIRequestListRender extends DefaultListCellRenderer {
    ParmGenMacroTrace pmt;

    public MacroBuilderUIRequestListRender(ParmGenMacroTrace pmt) {
        this.pmt = pmt;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label =
                (JLabel)
                        super.getListCellRendererComponent(
                                list, value, index, isSelected, cellHasFocus);

        boolean isRunning = pmt.getRunningStepNo() == index;
        if (isRunning && index >= 0) {
            label.setIcon(A_TAB_ICON);
        } else {
            label.setIcon(null);
        }

        if (list.isSelectedIndex(index)) {
            // 選択行はデフォルトの色
            label.setBackground(list.getSelectionBackground());
        } else {
            label.setBackground(list.getBackground());
        }
        if (pmt.isDisabledRequest(index)) {
            label.setForeground(Color.LIGHT_GRAY);
        } else if (pmt.isCurrentRequest(index)) {
            label.setForeground(Color.BLUE);
        } else if (pmt.isError(index)) {
            label.setForeground(Color.RED);
        } else {
            label.setForeground(list.getForeground());
        }
        return label;
    }
}
