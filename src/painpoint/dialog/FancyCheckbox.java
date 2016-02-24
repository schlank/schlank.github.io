package painpoint.dialog;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class FancyCheckbox extends JComponent implements MouseListener {

    private boolean selected;
    private String selectedLabel;
    private String unSelectedLabel;
    private ImageIcon selectedIcon;
    private ImageIcon unSelectedIcon;
    private JLabel lblIcon;
    private JLabel lblText;

    public FancyCheckbox(String selectedLabel, String unSelectedLabel,
                           ImageIcon selectedIcon, ImageIcon unSelectedIcon) {
        this.selectedLabel = selectedLabel;
        this.unSelectedLabel = unSelectedLabel;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;

        createComponents();
    }

    private void createComponents() {
        selected = false;
        lblIcon = new JLabel(unSelectedIcon);
        lblText = new JLabel(unSelectedLabel);
        setLayout(new FlowLayout());
        add(lblIcon);
        add(lblText);
        lblIcon.addMouseListener(this);
        lblText.addMouseListener(this);
    }

    private void updateLabels() {
        lblText.setText(selected ? selectedLabel : unSelectedLabel);
        lblIcon.setIcon(selected ? selectedIcon : unSelectedIcon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == lblIcon || source == lblText) {
            selected = !selected;
            updateLabels();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void addMouseListener(MouseListener listener) {
        super.addMouseListener(listener);
        lblIcon.addMouseListener(listener);
        lblText.addMouseListener(listener);
    }

    @Override
    public void removeMouseListener(MouseListener listener) {
        super.removeMouseListener(listener);
        lblIcon.removeMouseListener(listener);
        lblText.removeMouseListener(listener);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        updateLabels();
    }

    public String getSelectedLabel() {
        return selectedLabel;
    }

    public String getUnSelectedLabel() {
        return unSelectedLabel;
    }

    public ImageIcon getSelectedIcon() {
        return selectedIcon;
    }

    public ImageIcon getUnSelectedIcon() {
        return unSelectedIcon;
    }

    public JLabel getLblIcon() {
        return lblIcon;
    }

    public JLabel getLblText() {
        return lblText;
    }

    public void setSelectedLabel(String selectedLabel) {
        this.selectedLabel = selectedLabel;
        updateLabels();
    }

    public void setUnSelectedLabel(String unSelectedLabel) {
        this.unSelectedLabel = unSelectedLabel;
        updateLabels();
    }

    public void setSelectedIcon(ImageIcon selectedIcon) {
        this.selectedIcon = selectedIcon;
        updateLabels();
    }

    public void setUnSelectedIcon(ImageIcon unSelectedIcon) {
        this.unSelectedIcon = unSelectedIcon;
        updateLabels();
    }

    public void setLblIcon(JLabel lblIcon) {
        this.lblIcon = lblIcon;
        updateLabels();
    }

    public void setLblText(JLabel lblText) {
        this.lblText = lblText;
        updateLabels();
    }

}